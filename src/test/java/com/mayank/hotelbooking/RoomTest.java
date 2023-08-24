package com.mayank.hotelbooking;

import com.mayank.hotelbooking.controllers.RoomController;
import com.mayank.hotelbooking.datastore.RoomData;
import com.mayank.hotelbooking.model.Room;
import com.mayank.hotelbooking.model.RoomInventory;
import com.mayank.hotelbooking.model.RoomType;
import com.mayank.hotelbooking.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class RoomTest {
    RoomController roomController;

    @BeforeEach
    void setup() {
        RoomService roomService = new RoomService(new RoomData());
        roomController = new RoomController(roomService);
    }

    @Test
    void RoomFlowTest() {
        Room room1 = new Room("room1", "HOTEL1", "Double room", RoomType.DOUBLE, 1, 123);
        Room room2 = new Room("room2", "HOTEL1", "Double room", RoomType.DOUBLE, 1, 124);
        roomController.addRoom(room1);
        roomController.addRoom(room2);

        RoomInventory roomInventory = new RoomInventory("ri1", "HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 2, 0);
        roomController.addRoomInventory(roomInventory);

        System.out.println(roomController.getRoom("room1"));
        System.out.println(roomController.getAllRoomInventory("HOTEL1", new Timestamp(1692804513000L)));

        room1 = new Room("room1", "HOTEL1", "Single room", RoomType.SINGLE, 1, 123);
        roomController.updateRoom(room1);
        System.out.println(roomController.getRoom("room1"));

        roomController.deleteRoom("room1");
    }
}
