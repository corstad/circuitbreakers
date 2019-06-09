package com.solutiondesign.circuitbreakers.dto;

import lombok.Data;

@Data
public class UserDto {
    int id;
    String name;
    String username;
    String email;
    AddressDto address;
    String phone;
    String website;
    CompanyDto company;

}