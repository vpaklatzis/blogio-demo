package com.vpaklatzis.blogio.service;

import com.vpaklatzis.blogio.DTO.SigninRequestDTO;
import com.vpaklatzis.blogio.DTO.SignupRequestDTO;
import com.vpaklatzis.blogio.exception.BlogioException;
import com.vpaklatzis.blogio.model.NotificationEmail;
import com.vpaklatzis.blogio.model.UserEntity;
import com.vpaklatzis.blogio.model.VerificationTokenEntity;
import com.vpaklatzis.blogio.repository.UserRepository;
import com.vpaklatzis.blogio.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void createUser(SignupRequestDTO signupRequestDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(signupRequestDTO.getUsername());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Activate your account",
                user.getEmail(), "Click on the following link to activate your account: " +
                "http://localhost:8080/api/auth/verify/" + token));
    }

    private String generateVerificationToken(UserEntity user) {
        String token = UUID.randomUUID().toString();

        VerificationTokenEntity verificationToken = new VerificationTokenEntity();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        return token;
    }

    public void verifyAccountWithToken(String token) {
        Optional<VerificationTokenEntity> verificationToken = verificationTokenRepository.findByToken(token);

        verificationToken.orElseThrow(() -> new BlogioException("Token is invalid"));

        fetchUserAndEnableAccount(verificationToken.get());
    }

    @Transactional
    public void fetchUserAndEnableAccount(VerificationTokenEntity verificationToken) {
        String username = verificationToken.getUser().getUsername();

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new BlogioException("User with name " + username + " not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void signIn(SigninRequestDTO signinRequestDTO) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signinRequestDTO.getUsername(), signinRequestDTO.getPassword()));
    }
}
