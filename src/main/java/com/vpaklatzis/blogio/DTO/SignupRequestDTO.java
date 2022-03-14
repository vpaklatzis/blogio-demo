package com.vpaklatzis.blogio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Will try to make the DTO immutable
@Getter
@AllArgsConstructor
public class SignupRequestDTO {

    private final String email;
    private final String username;
    private final String password;
}
