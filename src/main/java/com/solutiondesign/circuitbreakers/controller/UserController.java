package com.solutiondesign.circuitbreakers.controller;

import com.solutiondesign.circuitbreakers.dto.UserDto;
import com.solutiondesign.circuitbreakers.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> findUser(@PathVariable(name = "userId") int userId) {
        log.info("Find User [{}]", userId);
        return ResponseEntity.ok().body(userService.findUser(userId));
    }
}
