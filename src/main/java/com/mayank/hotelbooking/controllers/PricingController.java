package com.mayank.hotelbooking.controllers;

import com.mayank.hotelbooking.model.Bill;
import com.mayank.hotelbooking.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PricingController {
    private PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping(value = "/pricing/{reservationId}/")
    public Bill getBill(final String reservationId) {
        return pricingService.getBill(reservationId);
    }
}