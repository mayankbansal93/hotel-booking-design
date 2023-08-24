package com.mayank.hotelbooking.controllers;

import com.mayank.hotelbooking.model.RateCard;
import com.mayank.hotelbooking.model.RoomType;
import com.mayank.hotelbooking.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class RateController {
    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping(value = "/rooms/roomType")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRate(@RequestBody final RateCard rateCard) {
        rateService.addRateCard(rateCard);
    }

    @GetMapping(value = "rooms/{roomId}/rate")
    public RateCard getRate(final String hotelId, final RoomType roomType, final Timestamp date) {
        return rateService.getRateCard(hotelId, roomType, date);
    }

    @PutMapping(value = "rooms/{roomId}/rate/update")
    public void updateRate(final RateCard rateCard) {
        rateService.updateRateCard(rateCard);
    }

    @PostMapping(value = "rooms/{roomId}/rate/delete")
    public void deleteRate(final String hotelId, final RoomType roomType, final Timestamp date) {
        rateService.deleteRateCard(hotelId, roomType, date);
    }
}
