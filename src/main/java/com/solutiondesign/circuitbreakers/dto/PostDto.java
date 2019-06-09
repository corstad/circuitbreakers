package com.solutiondesign.circuitbreakers.dto;

import lombok.Data;

@Data
public class PostDto {
    int id;
    int userId;
    String title;
    String body;
}
