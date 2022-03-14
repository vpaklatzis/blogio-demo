package com.vpaklatzis.blogio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Will try to make the DTO immutable
@Getter
@AllArgsConstructor
public class AuthenticationResponseDTO {

    private final String authenticationToken;
    private final String username;
}
