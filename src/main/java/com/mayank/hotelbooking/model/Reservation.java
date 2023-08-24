package com.mayank.hotelbooking.model;

import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Map;

@Getter
@ToString
public class Reservation {
    private String id;
    private String hotelId;
    private String userId;
    private Map<RoomType, Integer> roomsBooked;
    private ReservationStatus status;
    private Timestamp startDate;
    private Timestamp endDate;

    public Reservation(String id, String hotelId, String userId, Map<RoomType, Integer> roomsBooked, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.hotelId= hotelId;
        this.userId = userId;
        this.roomsBooked = roomsBooked;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;
    }

    public void markReservationCancelled() {
        status = ReservationStatus.CANCELED;
    }

    public void markReservationCompleted() {
        status = ReservationStatus.COMPLETED;
    }

    public void markReservationRejected() {
        status = ReservationStatus.REJECTED;
    }

    public void markReservationWaitingForPayment() {
        status = ReservationStatus.WAITING_FOR_PAYMENT;
    }
}
