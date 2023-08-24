package com.mayank.hotelbooking.controllers;

import com.mayank.hotelbooking.model.Hotel;
import com.mayank.hotelbooking.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(value = "/hotel")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHotel(@RequestBody final Hotel hotel) {
        hotelService.addHotel(hotel);
    }

    @GetMapping(value = "/hotels")
    public List<Hotel> getAllHotel() {
        return hotelService.getAllHotels();
    }

    @GetMapping(value = "/hotel/{hotelId}")
    public Hotel getHotelById(final String hotelId) {
        return hotelService.getHotelById(hotelId);
    }

    @GetMapping(value = "/hotels/name/{hotelName}")
    public List<Hotel> getHotelsByName(final String hotelName) {
        return hotelService.getHotelsByName(hotelName);
    }

    @GetMapping(value = "/hotels/city/{city}")
    public List<Hotel> getHotelsByCity(final String city) {
        return hotelService.getHotelsByCity(city);
    }

    @PutMapping(value = "/hotel/update")
    public void updateHotel(final Hotel hotel) {
        hotelService.updateHotel(hotel);
    }

    @PostMapping(value = "/hotel/delete")
    public void deleteHotel(final String hotelId) {
        hotelService.deleteHotel(hotelId);
    }
}
