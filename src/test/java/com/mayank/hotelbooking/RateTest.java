package com.mayank.hotelbooking;

import com.mayank.hotelbooking.controllers.RateController;
import com.mayank.hotelbooking.datastore.RateCardData;
import com.mayank.hotelbooking.model.RateCard;
import com.mayank.hotelbooking.model.RoomType;
import com.mayank.hotelbooking.services.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class RateTest {
    RateController rateController;

    @BeforeEach
    void setup() {
        RateService rateService = new RateService(new RateCardData());
        rateController = new RateController(rateService);
    }

    @Test
    void RateCardFlowTest() {
        RateCard rateCard1 = new RateCard("HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 3000);
        rateController.addRate(rateCard1);

        System.out.println(rateController.getRate("HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L)));

        rateCard1 = new RateCard("HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L), 3500);
        rateController.updateRate(rateCard1);
        System.out.println(rateController.getRate("HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L)));

        rateController.deleteRate("HOTEL1", RoomType.DOUBLE, new Timestamp(1692804513000L));
    }
}
