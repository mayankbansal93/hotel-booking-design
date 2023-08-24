package com.mayank.hotelbooking.datastore;
import lombok.Getter;
import org.springframework.stereotype.Component;

import com.mayank.hotelbooking.model.User;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class UserData {
    private Map<String, User> userById = new HashMap<>();
}
