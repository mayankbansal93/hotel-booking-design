package com.mayank.hotelbooking;

import com.mayank.hotelbooking.controllers.HotelController;
import com.mayank.hotelbooking.datastore.HotelData;
import com.mayank.hotelbooking.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HotelTest {
    HotelController hotelController;

    @BeforeEach
    void setup() {
        HotelService hotelService = new HotelService(new HotelData());
        hotelController = new HotelController(hotelService);
    }

    @Test
    void HotelFlowTest() {
        hotelController.addHotel(TestHelper.buildHotel("HOTEL1", "Hotel 1"));
        hotelController.addHotel(TestHelper.buildHotel("HOTEL2", "Hotel 2"));
        hotelController.addHotel(TestHelper.buildHotel("HOTEL3", "Hotel 2"));

        System.out.println(hotelController.getAllHotel());

        System.out.println(hotelController.getHotelById("HOTEL2"));

        System.out.println(hotelController.getHotelsByName("Hotel 2"));

        System.out.println(hotelController.getHotelsByCity("CITY"));

        hotelController.updateHotel(TestHelper.buildHotel("HOTEL1", "Hotel 4"));
        hotelController.deleteHotel("HOTEL1");
    }
}
