package com.solutiondesign.circuitbreakers.connector;

import com.solutiondesign.circuitbreakers.dto.PostDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@CircuitBreaker(name = "posts")
@Retry(name = "posts")
@Component(value = "postsConnector")
@Slf4j
public class PostsConnector {
    private String postsUrl;
    private RestTemplate restTemplate;

    @Autowired
    public PostsConnector(@Value("${services.endpoints.posts}") final String postsUrl, final RestTemplate restTemplate) {
        this.postsUrl = postsUrl;
        this.restTemplate = restTemplate;
    }

    public PostDto findPost(final int postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity(headers);

        log.info("Making call to [{}] to retrieve post id [{}]", postsUrl, postId);
        ResponseEntity<PostDto> exchange = restTemplate.exchange(postsUrl + "/{postId}", HttpMethod.GET, entity, PostDto.class, postId);
        PostDto body = exchange.getBody();
        log.info("Returned from [{}] with [{}]", postsUrl, body);

        return body;
    }
}
