package com.mayank.hotelbooking.controllers;

import com.mayank.hotelbooking.model.Reservation;
import com.mayank.hotelbooking.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/reservation")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(@RequestBody final Reservation reservation) {
        reservationService.addReservation(reservation);
    }

    @GetMapping(value = "/reservations/{reservationId}")
    public Reservation getReservation(final String reservationId) {
        return reservationService.getReservation(reservationId);
    }

    @GetMapping(value = "/reservations/{hotelId}")
    public List<Reservation> getAllReservationsByHotelId(final String hotelId) {
        return reservationService.getAllReservationsByHotelId(hotelId);
    }

    @GetMapping(value = "/reservations/{userId}")
    public List<Reservation> getAllReservationsByUserId(final String userId) {
        return reservationService.getAllReservationsByUserId(userId);
    }

    @PostMapping(value = "/reservations/delete")
    public void deleteReservation(final String reservationId) {
        reservationService.cancelReservation(reservationId);
    }
}
