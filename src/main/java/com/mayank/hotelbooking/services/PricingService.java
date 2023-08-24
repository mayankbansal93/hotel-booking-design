package com.mayank.hotelbooking.services;

import com.mayank.hotelbooking.model.Bill;
import com.mayank.hotelbooking.model.RateCard;
import com.mayank.hotelbooking.model.Reservation;
import com.mayank.hotelbooking.model.RoomType;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

@Service
public class PricingService {
    private RateService rateService;
    private ReservationService reservationService;

    @Autowired
    public PricingService(RateService rateService, ReservationService reservationService) {
        this.rateService = rateService;
        this.reservationService = reservationService;
    }

    public Bill getBill(@NonNull final String reservationId) {
        Reservation reservation = reservationService.getReservation(reservationId);
        double totalCost = 0.0;
        for(long t = reservation.getStartDate().getTime(); t <= reservation.getEndDate().getTime(); t++){
            for(Map.Entry<RoomType, Integer> me: reservation.getRoomsBooked().entrySet()) {
                RateCard rateCard = rateService.getRateCard(reservation.getHotelId(), me.getKey(), new Timestamp(t));
                totalCost += rateCard.getPrice() * me.getValue();
            }
        }

        return Bill.builder()
                .id(UUID.randomUUID().toString())
                .amountToBePaid(totalCost)
                .tax(totalCost * 0.05)
                .build();
    }
}