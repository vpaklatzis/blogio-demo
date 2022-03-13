package com.vpaklatzis.blogio.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubredditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subredditId;

    @NotBlank(message = "Subreddit name value cannot be empty or Null")
    private String subredditName;

    @NotBlank(message = "Subreddit description value cannot be empty or Null")
    private String subredditDescription;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PostEntity> subredditPosts;

    private Instant subredditCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
