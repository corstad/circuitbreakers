package com.solutiondesign.circuitbreakers.controller;

import com.solutiondesign.circuitbreakers.dto.PostDto;
import com.solutiondesign.circuitbreakers.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> findPost(@PathVariable(name = "postId") int postId) {
        log.info("Find Post [{}]", postId);
        return ResponseEntity.ok().body(postService.findPost(postId));
    }
}
