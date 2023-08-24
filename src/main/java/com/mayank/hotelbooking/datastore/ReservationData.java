package com.mayank.hotelbooking.datastore;

import com.mayank.hotelbooking.model.Hotel;
import com.mayank.hotelbooking.model.Reservation;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class ReservationData {
    private Map<String, Reservation> reservationById = new HashMap<>();
    private Map<String, List<String>> reservationIdsByUserId = new HashMap<>();
    private Map<String, List<String>> reservationIdsByHotelId = new HashMap<>();
}
