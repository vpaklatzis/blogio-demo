package com.vpaklatzis.blogio.service;

import com.vpaklatzis.blogio.DTO.SubredditDTO;
import com.vpaklatzis.blogio.model.SubredditEntity;
import com.vpaklatzis.blogio.repository.SubredditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDTO createSubreddit(SubredditDTO subredditDTO) {
        SubredditEntity subreddit = SubredditEntity.builder()
                .subredditName(subredditDTO.getName())
                .subredditDescription(subredditDTO.getDescription())
                .build();

        SubredditEntity savedSubreddit = subredditRepository.save(subreddit);
        subredditDTO.setId(savedSubreddit.getSubredditId());

        return subredditDTO;
    }

    @Transactional(readOnly = true)
    public List<SubredditDTO> getAllSubreddits() {
        return subredditRepository.findAll().stream().map(this::mapSubredditEntityToDTO).collect(toList());
    }

    private SubredditDTO mapSubredditEntityToDTO(SubredditEntity subredditEntity) {
        return SubredditDTO.builder()
                .name(subredditEntity.getSubredditName())
                .id(subredditEntity.getSubredditId())
                .postTotalNumber(subredditEntity.getSubredditPosts().size())
                .build();
    }
}
