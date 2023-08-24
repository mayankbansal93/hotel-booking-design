package com.mayank.hotelbooking;

import com.mayank.hotelbooking.model.Address;
import com.mayank.hotelbooking.model.Hotel;
import com.mayank.hotelbooking.model.User;

public class TestHelper {
    public static User buildUser(String id, String name) {
        return User.builder()
                .id(id)
                .name(name)
                .phoneNo(9988998899L)
                .build();
    }

    public static Hotel buildHotel(String id, String name) {
        return Hotel.builder()
                .id(id)
                .name(name)
                .address(buildAddress("ADD1", "CITY"))
                .build();
    }

    public static Address buildAddress(String id, String city) {
        return Address.builder()
                .id(id)
                .city(city)
                .build();
    }
}
