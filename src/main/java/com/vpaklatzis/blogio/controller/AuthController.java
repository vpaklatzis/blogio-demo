package com.vpaklatzis.blogio.controller;

import com.vpaklatzis.blogio.DTO.SignupRequestDTO;
import com.vpaklatzis.blogio.model.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void Signup(@RequestBody SignupRequestDTO signupRequestDTO) {

    }
}
