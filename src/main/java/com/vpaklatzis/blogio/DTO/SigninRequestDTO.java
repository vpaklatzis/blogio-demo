package com.vpaklatzis.blogio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Will try to make the DTO immutable
@Getter
@AllArgsConstructor
public class SigninRequestDTO {

    private final String username;
    private final String password;
}
