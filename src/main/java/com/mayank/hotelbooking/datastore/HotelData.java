package com.mayank.hotelbooking.datastore;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mayank.hotelbooking.model.Hotel;

@Component
@Getter
public class HotelData {
    private Map<String, Hotel> hotelById = new HashMap<>();
    private Map<String, List<String>> hotelIdsByName = new HashMap<>();
    private Map<String, List<String>> HotelIdsByCity = new HashMap<>();
}
