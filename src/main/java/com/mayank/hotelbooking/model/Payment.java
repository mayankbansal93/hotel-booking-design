package com.mayank.hotelbooking.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class Payment {
    private String id;
    private String reservationId;
    private Map<PaymentType, Double> amountPaid;
    private PaymentStatus paymentStatus;

    public Payment(String id, String reservationId, Map<PaymentType, Double> amountPaid) {
        this.id = id;
        this.reservationId = reservationId;
        this.amountPaid = amountPaid;
        paymentStatus = PaymentStatus.PENDING;
    }

    public void markPaymentDeclined() {
        paymentStatus = PaymentStatus.DECLINED;
    }

    public void markPaymentApproved() {
        paymentStatus = PaymentStatus.APPROVED;
    }

    public void markPaymentTimeout() {
        paymentStatus = PaymentStatus.TIMEOUT;
    }
}