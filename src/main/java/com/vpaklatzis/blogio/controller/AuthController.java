package com.vpaklatzis.blogio.controller;

import com.vpaklatzis.blogio.DTO.SigninRequestDTO;
import com.vpaklatzis.blogio.DTO.SignupRequestDTO;
import com.vpaklatzis.blogio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        authService.createUser(signupRequestDTO);

        return "User created successfully";
    }

    @GetMapping("/verify/{token}")
    @ResponseStatus(HttpStatus.OK)
    public String verifyAccount(@PathVariable String token) {
        authService.verifyAccountWithToken(token);

        return "Account verified successfully";
    }

    @GetMapping("/signin")
    public void signin(@RequestBody SigninRequestDTO signinRequestDTO) {
        authService.signIn(signinRequestDTO);
    }
}
