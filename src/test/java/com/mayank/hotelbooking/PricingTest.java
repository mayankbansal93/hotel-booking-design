package com.mayank.hotelbooking;

import com.mayank.hotelbooking.controllers.PricingController;
import com.mayank.hotelbooking.controllers.RateController;
import com.mayank.hotelbooking.controllers.ReservationController;
import com.mayank.hotelbooking.controllers.RoomController;
import com.mayank.hotelbooking.datastore.RateCardData;
import com.mayank.hotelbooking.datastore.ReservationData;
import com.mayank.hotelbooking.datastore.RoomData;
import com.mayank.hotelbooking.model.RateCard;
import com.mayank.hotelbooking.model.Reservation;
import com.mayank.hotelbooking.model.RoomInventory;
import com.mayank.hotelbooking.model.RoomType;
import com.mayank.hotelbooking.services.PricingService;
import com.mayank.hotelbooking.services.RateService;
import com.mayank.hotelbooking.services.ReservationService;
import com.mayank.hotelbooking.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Map;

public class PricingTest {
    PricingController pricingController;
    ReservationController reservationController;
    RoomController roomController;
    RateController rateController;

    @BeforeEach
    void setup() {
        RateService rateService = new RateService(new RateCardData());
        RoomService roomService = new RoomService(new RoomData());
        ReservationService reservationService = new ReservationService(new ReservationData(), roomService);
        PricingService pricingService = new PricingService(rateService, reservationService);
        pricingController = new PricingController(pricingService);
        reservationController = new ReservationController(reservationService);
        roomController = new RoomController(roomService);
        rateController = new RateController(rateService);
    }

    @Test
    void PricingFlowTest() {
        RoomInventory roomInventory = new RoomInventory("ri1", "HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 2, 0);
        roomController.addRoomInventory(roomInventory);

        Reservation reservation = new Reservation("RES1", "HOTEL1", "USER1",
                Map.of(RoomType.DOUBLE, 2), new Timestamp(1692804513000L), new Timestamp(1692804513000L));
        reservationController.addReservation(reservation);

        RateCard rateCard1 = new RateCard("HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 3000);
        rateController.addRate(rateCard1);

        System.out.println(pricingController.getBill("RES1"));
    }
}
