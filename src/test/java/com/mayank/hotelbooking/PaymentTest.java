package com.mayank.hotelbooking;

import com.mayank.hotelbooking.controllers.PaymentController;
import com.mayank.hotelbooking.controllers.RateController;
import com.mayank.hotelbooking.controllers.ReservationController;
import com.mayank.hotelbooking.controllers.RoomController;
import com.mayank.hotelbooking.datastore.PaymentData;
import com.mayank.hotelbooking.datastore.RateCardData;
import com.mayank.hotelbooking.datastore.ReservationData;
import com.mayank.hotelbooking.datastore.RoomData;
import com.mayank.hotelbooking.model.*;
import com.mayank.hotelbooking.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Map;

public class PaymentTest {
    PaymentController paymentController;
    ReservationController reservationController;
    RoomController roomController;
    RateController rateController;

    @BeforeEach
    void setup() {
        RateService rateService = new RateService(new RateCardData());
        RoomService roomService = new RoomService(new RoomData());
        ReservationService reservationService = new ReservationService(new ReservationData(), roomService);
        PricingService pricingService = new PricingService(rateService, reservationService);
        PaymentService paymentService = new PaymentService(new PaymentData(), reservationService, pricingService);
        paymentController = new PaymentController(paymentService);
        reservationController = new ReservationController(reservationService);
        roomController = new RoomController(roomService);
        rateController = new RateController(rateService);
    }

    @Test
    void PaymentFlowTest() {
        RoomInventory roomInventory = new RoomInventory("ri1", "HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 2, 0);
        roomController.addRoomInventory(roomInventory);

        Reservation reservation = new Reservation("RES1", "HOTEL1", "USER1",
                Map.of(RoomType.DOUBLE, 2), new Timestamp(1692804513000L), new Timestamp(1692804513000L));
        reservationController.addReservation(reservation);

        RateCard rateCard1 = new RateCard("HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 3000);
        rateController.addRate(rateCard1);

        Payment payment = new Payment("PAY1", "RES1", Map.of(PaymentType.CREDIT_CARD, 6000.0));
        paymentController.addPayment(payment);

        System.out.println(paymentController.getPaymentById("PAY1"));

        System.out.println(paymentController.getPaymentByReservationId("RES1"));
    }
}
