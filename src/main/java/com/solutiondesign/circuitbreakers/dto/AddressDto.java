package com.solutiondesign.circuitbreakers.dto;

import lombok.Data;

@Data
public class AddressDto {
    String street;
    String suite;
    String city;
    String zipcode;
    GeoDto geo;
}
