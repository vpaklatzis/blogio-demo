package com.vpaklatzis.blogio.controller;

import com.vpaklatzis.blogio.DTO.PostDTO;
import com.vpaklatzis.blogio.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO create(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/all/{subredditId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPostsBySubredditId(@PathVariable Long subredditId) {
        return postService.getPostsBySubredditId(subredditId);
    }

    @GetMapping("/all/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPostsByUsername(String username) {
        return postService.getPostsByUsername(username);
    }

}
