package com.vpaklatzis.blogio.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SubredditDTO {

    private Long id;
    private final String name;
    private final String description;
    private final Integer postTotalNumber;
}
