package com.vpaklatzis.blogio.service;

import com.vpaklatzis.blogio.DTO.PostDTO;
import com.vpaklatzis.blogio.exception.SubredditNotFoundException;
import com.vpaklatzis.blogio.model.PostEntity;
import com.vpaklatzis.blogio.model.SubredditEntity;
import com.vpaklatzis.blogio.repository.PostRepository;
import com.vpaklatzis.blogio.repository.SubredditRepository;
import com.vpaklatzis.blogio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public PostDTO createPost(PostDTO postDTO) {
        SubredditEntity subreddit = subredditRepository.findByName(postDTO.getSubreddit()).orElseThrow(() ->
                new SubredditNotFoundException("No subreddit found with name: " + postDTO.getSubreddit()));

        PostEntity post = PostEntity.builder()
                .postCreatedDate(Instant.now())
                .postDescription(postDTO.getDescription())
                .subreddit(subreddit)
                .postVoteCount(0)
                .user(authService.getCurrentUser())
                .build();

        postRepository.save(post);

        return postDTO;
    }

    @Transactional(readOnly = true)
    public List<PostDTO> getPostsBySubredditId(Long subredditId) {
    }

    @Transactional(readOnly = true)
    public List<PostDTO> getPostsByUsername(String username) {
    }

    @Transactional(readOnly = true)
    public List<PostDTO> getAllPosts() {
    }

    @Transactional(readOnly = true)
    public PostDTO getPostById(Long id) {
    }
}
