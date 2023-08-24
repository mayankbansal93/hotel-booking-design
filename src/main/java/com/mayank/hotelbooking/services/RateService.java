package com.mayank.hotelbooking.services;

import com.mayank.hotelbooking.datastore.RateCardData;
import com.mayank.hotelbooking.exceptions.HotelBookingException;
import com.mayank.hotelbooking.model.RateCard;
import com.mayank.hotelbooking.model.RoomType;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static com.mayank.hotelbooking.exceptions.ExceptionType.RATE_CARD_ALREADY_EXISTS;
import static com.mayank.hotelbooking.exceptions.ExceptionType.RATE_CARD_NOT_FOUND;

@Service
public class RateService {
    private RateCardData rateCardData;

    @Autowired
    public RateService(RateCardData rateCardData) {
        this.rateCardData = rateCardData;
    }

    public void addRateCard(@NonNull final RateCard rateCard) {
        if (rateCardData.getRateCard().containsKey(rateCard.getHotelId()) &&
                rateCardData.getRateCard().get(rateCard.getHotelId()).containsKey(rateCard.getRoomType()) &&
                rateCardData.getRateCard().get(rateCard.getHotelId()).get(rateCard.getRoomType()).containsKey(rateCard.getDate())) {
            throw new HotelBookingException(RATE_CARD_ALREADY_EXISTS, "Duplicate rate card");
        }
        Map<RoomType, Map<Timestamp, RateCard>> roomTypeToDateToPriceMap = rateCardData.getRateCard().getOrDefault(rateCard.getHotelId(), new HashMap<>());
        Map<Timestamp, RateCard> dateToPriceMap = roomTypeToDateToPriceMap.getOrDefault(rateCard.getRoomType(), new HashMap<>());
        dateToPriceMap.put(rateCard.getDate(), rateCard);
        roomTypeToDateToPriceMap.put(rateCard.getRoomType(), dateToPriceMap);
        rateCardData.getRateCard().put(rateCard.getHotelId(), roomTypeToDateToPriceMap);
    }

    public RateCard getRateCard(@NonNull final String hotelId, @NonNull final RoomType roomType, @NonNull final Timestamp date) {
        return rateCardData.getRateCard().get(hotelId).get(roomType).get(date);
    }

    public void updateRateCard(@NonNull final RateCard rateCard) {
        if (!(rateCardData.getRateCard().containsKey(rateCard.getHotelId()) &&
                rateCardData.getRateCard().get(rateCard.getHotelId()).containsKey(rateCard.getRoomType()) &&
                rateCardData.getRateCard().get(rateCard.getHotelId()).get(rateCard.getRoomType()).containsKey(rateCard.getDate()))) {
            throw new HotelBookingException(RATE_CARD_NOT_FOUND, "rate card not found");
        }
        rateCardData.getRateCard().get(rateCard.getHotelId()).get(rateCard.getRoomType()).put(rateCard.getDate(), rateCard);
    }

    public void deleteRateCard(@NonNull final String hotelId, @NonNull final RoomType roomType, @NonNull final Timestamp date) {
        if (!(rateCardData.getRateCard().containsKey(hotelId) &&
                rateCardData.getRateCard().get(hotelId).containsKey(roomType) &&
                rateCardData.getRateCard().get(hotelId).get(roomType).containsKey(date))) {
            throw new HotelBookingException(RATE_CARD_NOT_FOUND, "rate card not found");
        }
        rateCardData.getRateCard().get(hotelId).get(roomType).remove(date);
    }
}
