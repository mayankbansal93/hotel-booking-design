package com.mayank.hotelbooking.services;

import com.mayank.hotelbooking.datastore.HotelData;
import com.mayank.hotelbooking.exceptions.HotelBookingException;
import com.mayank.hotelbooking.model.Hotel;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mayank.hotelbooking.exceptions.ExceptionType.HOTEL_ALREADY_EXISTS;
import static com.mayank.hotelbooking.exceptions.ExceptionType.HOTEL_NOT_FOUND;

@Service
public class HotelService {
    private HotelData hotelData;

    @Autowired
    public HotelService(HotelData hotelData) {
        this.hotelData = hotelData;
    }

    public void addHotel(@NonNull final Hotel hotel) {
        if (hotelData.getHotelById().containsKey(hotel.getId())) {
            throw new HotelBookingException(HOTEL_ALREADY_EXISTS, "Duplicate Hotel");
        }
        hotelData.getHotelById().put(hotel.getId(), hotel);

        List<String> hotelIds = hotelData.getHotelIdsByName()
                .getOrDefault(hotel.getName(), new ArrayList<>());
        hotelIds.add(hotel.getId());
        hotelData.getHotelIdsByName().put(hotel.getName(), hotelIds);

        List<String> hotelsByCity = hotelData.getHotelIdsByCity()
                .getOrDefault(hotel.getAddress().getCity(), new ArrayList<>());
        hotelsByCity.add(hotel.getId());
        hotelData.getHotelIdsByCity().put(hotel.getAddress().getCity(), hotelsByCity);
    }

    public List<Hotel> getAllHotels() {
        return new ArrayList<>(hotelData.getHotelById().values());
    }

    public Hotel getHotelById(@NonNull final String hotelId) {
        if (!hotelData.getHotelById().containsKey(hotelId)) {
            throw new HotelBookingException(HOTEL_NOT_FOUND, "Hotel doesn't exist");
        }
        return hotelData.getHotelById().get(hotelId);
    }

    public List<Hotel> getHotelsByName(@NonNull final String hotelName) {
        List<Hotel> hotels = new ArrayList<>();
        for (String hotelId : hotelData.getHotelIdsByName().get(hotelName)) {
            hotels.add(getHotelById(hotelId));
        }
        return hotels;
    }

    public List<Hotel> getHotelsByCity(@NonNull final String city) {
        List<Hotel> hotels = new ArrayList<>();
        for (String hotelId : hotelData.getHotelIdsByCity().get(city)) {
            hotels.add(getHotelById(hotelId));
        }
        return hotels;
    }

    public void updateHotel(@NonNull final Hotel hotel) {
        if (!hotelData.getHotelById().containsKey(hotel.getId())) {
            throw new HotelBookingException(HOTEL_NOT_FOUND, "hotel not found");
        }
        hotelData.getHotelById().put(hotel.getId(), hotel);

        List<String> hotelIds = hotelData.getHotelIdsByName()
                .getOrDefault(hotel.getName(), new ArrayList<>());
        hotelIds.add(hotel.getId());
        hotelData.getHotelIdsByName().put(hotel.getName(), hotelIds);

        List<String> hotelsByCity = hotelData.getHotelIdsByCity()
                .getOrDefault(hotel.getAddress().getCity(), new ArrayList<>());
        hotelsByCity.add(hotel.getId());
        hotelData.getHotelIdsByCity().put(hotel.getAddress().getCity(), hotelsByCity);
    }

    public void deleteHotel(@NonNull final String hotelId) {
        if (!hotelData.getHotelById().containsKey(hotelId)) {
            throw new HotelBookingException(HOTEL_NOT_FOUND, "hotel not found");
        }
        String hotelName = hotelData.getHotelById().get(hotelId).getName();
        String hotelCity = hotelData.getHotelById().get(hotelId).getAddress().getCity();
        hotelData.getHotelById().remove(hotelId);
        hotelData.getHotelIdsByName().get(hotelName).remove(hotelId);
        hotelData.getHotelIdsByCity().get(hotelCity).remove(hotelId);
    }
}
