package com.mayank.hotelbooking.datastore;

import com.mayank.hotelbooking.model.Payment;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class PaymentData {
    private Map<String, Payment> paymentById = new HashMap<>();
    private Map<String, String> paymentIdByReservationId = new HashMap<>();
}
