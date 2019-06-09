package com.solutiondesign.circuitbreakers.service;

import com.solutiondesign.circuitbreakers.connector.UsersConnector;
import com.solutiondesign.circuitbreakers.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UsersConnector usersConnector;

    public UserService(UsersConnector usersConnector) {
        this.usersConnector = usersConnector;
    }

    public UserDto findUser(final int userId) {
        return usersConnector.findUser(userId);
    }
}
