package com.mayank.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class Address {
    private String id;
    private String streetAddress;
    private String city;
    private String zipCode;
    private Location location;
}
