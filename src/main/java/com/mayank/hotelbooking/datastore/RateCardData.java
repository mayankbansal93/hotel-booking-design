package com.mayank.hotelbooking.datastore;

import com.mayank.hotelbooking.model.RateCard;
import com.mayank.hotelbooking.model.RoomType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class RateCardData {
    private Map<String, Map<RoomType, Map<Timestamp, RateCard>>> rateCard = new HashMap<>();
}
