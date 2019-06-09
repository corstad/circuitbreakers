package com.solutiondesign.circuitbreakers.service;

import com.solutiondesign.circuitbreakers.connector.PostsConnector;
import com.solutiondesign.circuitbreakers.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostsConnector postsConnector;

    @Autowired
    public PostService(final PostsConnector postsConnector) {
        this.postsConnector = postsConnector;
    }

    public PostDto findPost(final int postId) {
        return postsConnector.findPost(postId);
    }
}
