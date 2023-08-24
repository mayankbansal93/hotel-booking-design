package com.mayank.hotelbooking.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Room {
    private String id;
    private String hotelId;
    private String description;
    private RoomType roomType;
    private int floorNumber;
    private int roomNumber;
    private boolean isAvailable;

    public Room(String id, String hotelId, String description, RoomType roomType, int floorNumber, int roomNumber) {
        this.id = id;
        this.hotelId = hotelId;
        this.description = description;
        this.roomType = roomType;
        this.floorNumber = floorNumber;
        this.roomNumber = roomNumber;
        this.isAvailable = true;
    }

    public void markRoomAvailable() {
        isAvailable = true;
    }

    public void markRoomUnavailable(){
        isAvailable = false;
    }
}

