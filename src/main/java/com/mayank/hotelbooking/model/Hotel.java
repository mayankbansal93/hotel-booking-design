package com.mayank.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class Hotel {
    private String id;
    private String name;
    private Address address;
}
