package com.vpaklatzis.blogio.controller;

import com.vpaklatzis.blogio.DTO.SubredditDTO;
import com.vpaklatzis.blogio.service.SubredditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public SubredditDTO create(@RequestBody SubredditDTO subredditDTO) {
        return subredditService.createSubreddit(subredditDTO);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SubredditDTO> getSubreddits() {
        return subredditService.getAllSubreddits();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubredditDTO getSubreddit(@PathVariable Long id) {
        return subredditService.getSubredditById(id);
    }
}
