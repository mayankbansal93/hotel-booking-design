package com.mayank.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class RateCard {
    private String hotelId;
    private RoomType roomType;
    private Timestamp date;
    private double price;
}
