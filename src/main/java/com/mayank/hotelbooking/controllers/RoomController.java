package com.mayank.hotelbooking.controllers;

import com.mayank.hotelbooking.model.Room;
import com.mayank.hotelbooking.model.RoomInventory;
import com.mayank.hotelbooking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/hotels/{hotelId}/room")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoom(@RequestBody final Room room) {
        roomService.addRoom(room);
    }

    @PostMapping(value = "/hotels/{hotelId}/roomInventory")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoomInventory(@RequestBody final RoomInventory roomInventory) {
        roomService.addRoomInventory(roomInventory);
    }

    @GetMapping(value = "/hotels/{hotelId}/rooms/{roomId}")
    public Room getRoom(final String roomId) {
        return roomService.getRoom(roomId);
    }

    @GetMapping(value = "/hotels/{hotelId}/roomInventory")
    public List<RoomInventory> getAllRoomInventory(final String hotelId, final Timestamp date) {
        return roomService.getAllRoomInventory(hotelId, date);
    }

    @PutMapping(value = "/hotels/{hotelId}/rooms/update")
    public void updateRoom(final Room room) {
        roomService.updateRoom(room);
    }

    @PostMapping(value = "/hotels/{hotelId}/rooms/delete")
    public void deleteRoom(final String roomId) {
        roomService.deleteRoom(roomId);
    }
}
