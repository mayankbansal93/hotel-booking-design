package com.mayank.hotelbooking;

import com.mayank.hotelbooking.controllers.ReservationController;
import com.mayank.hotelbooking.controllers.RoomController;
import com.mayank.hotelbooking.datastore.ReservationData;
import com.mayank.hotelbooking.datastore.RoomData;
import com.mayank.hotelbooking.model.Reservation;
import com.mayank.hotelbooking.model.RoomInventory;
import com.mayank.hotelbooking.model.RoomType;
import com.mayank.hotelbooking.services.ReservationService;
import com.mayank.hotelbooking.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Map;

public class ReservationTest {
    ReservationController reservationController;
    RoomController roomController;

    @BeforeEach
    void setup() {
        RoomService roomService = new RoomService(new RoomData());
        ReservationService reservationService = new ReservationService(new ReservationData(), roomService);
        reservationController = new ReservationController(reservationService);
        roomController = new RoomController(roomService);
    }

    @Test
    void ReservationFlowTest() {
        RoomInventory roomInventory = new RoomInventory("ri1", "HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 2, 0);
        roomController.addRoomInventory(roomInventory);

        Reservation reservation = new Reservation("RES1", "HOTEL1", "USER1",
                Map.of(RoomType.DOUBLE, 2), new Timestamp(1692804513000L), new Timestamp(1692804513000L));
        reservationController.addReservation(reservation);

        System.out.println(reservationController.getReservation("RES1"));

        System.out.println(reservationController.getAllReservationsByUserId("USER1"));
        System.out.println(reservationController.getAllReservationsByHotelId("HOTEL1"));

        reservationController.deleteReservation("RES1");
    }
}
