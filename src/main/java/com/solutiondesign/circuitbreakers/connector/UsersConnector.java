package com.solutiondesign.circuitbreakers.connector;

import com.solutiondesign.circuitbreakers.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This connector shows the useage of the Resilience4J CircuitBreaker and Retry annotations.
 * <p>
 * By placing the annotation at the class level, all public methods will be wrapped.
 */
@CircuitBreaker(name = "users")
@Retry(name = "users")
@Component(value = "usersConnector")
@Slf4j
public class UsersConnector {

    private String usersUrl;
    private RestTemplate restTemplate;

    @Autowired
    public UsersConnector(@Value("${services.endpoints.users}") final String usersUrl, final RestTemplate restTemplate) {
        this.usersUrl = usersUrl;
        this.restTemplate = restTemplate;
    }

    public UserDto findUser(final int userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity(headers);

        log.info("Making call to [{}] to retrieve user id [{}]", usersUrl, userId);
        ResponseEntity<UserDto> exchange = restTemplate.exchange(usersUrl + "/{userId}", HttpMethod.GET, entity, UserDto.class, userId);
        UserDto body = exchange.getBody();
        log.info("Returned from [{}] with [{}]", usersUrl, body);

        return body;
    }
}
