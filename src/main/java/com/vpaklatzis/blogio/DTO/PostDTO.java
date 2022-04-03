package com.vpaklatzis.blogio.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostDTO {

    private final Long id;
    private final String subreddit;
    private final String post;
    private final String url;
    private final String description;
}
