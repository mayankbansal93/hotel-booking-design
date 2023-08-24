package com.mayank.hotelbooking.services;

import static com.mayank.hotelbooking.exceptions.ExceptionType.USER_ALREADY_EXISTS;
import static com.mayank.hotelbooking.exceptions.ExceptionType.USER_NOT_FOUND;

import com.mayank.hotelbooking.exceptions.HotelBookingException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mayank.hotelbooking.datastore.UserData;
import com.mayank.hotelbooking.model.User;

@Service
public class UserService {
    private UserData userData;

    @Autowired
    public UserService(UserData userData) {
        this.userData = userData;
    }

    public void addUser(@NonNull final User user) {
        if (userData.getUserById().containsKey(user.getId())) {
            throw new HotelBookingException(USER_ALREADY_EXISTS, "userId already exists");
        }
        userData.getUserById().put(user.getId(), user);
    }

    public void deleteUser(@NonNull final String userId) {
        if (!userData.getUserById().containsKey(userId)) {
            throw new HotelBookingException(USER_NOT_FOUND, "user not found");
        }
        userData.getUserById().remove(userId);
    }

    public void updateUser(@NonNull final User user) {
        if (!userData.getUserById().containsKey(user.getId())) {
            throw new HotelBookingException(USER_NOT_FOUND, "user not found");
        }
        userData.getUserById().put(user.getId(), user);
    }

    public User getUser(@NonNull final String userId) {
        if (!userData.getUserById().containsKey(userId)) {
            throw new HotelBookingException(USER_NOT_FOUND, "user not found");
        }
        return userData.getUserById().get(userId);
    }
}
