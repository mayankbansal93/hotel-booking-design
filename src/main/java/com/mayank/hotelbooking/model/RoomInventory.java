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
public class RoomInventory {
    private String id;
    private String hotelId;
    private RoomType roomType;
    private Timestamp date;
    private int totalInventory;
    private int reservedInventory;

    public void setReservedInventory(int reservedInventory) {
        this.reservedInventory = reservedInventory;
    }
}
