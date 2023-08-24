package com.mayank.hotelbooking.services;

import com.mayank.hotelbooking.datastore.PaymentData;
import com.mayank.hotelbooking.exceptions.HotelBookingException;
import com.mayank.hotelbooking.model.Bill;
import com.mayank.hotelbooking.model.Payment;
import com.mayank.hotelbooking.model.Reservation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.mayank.hotelbooking.exceptions.ExceptionType.PAYMENT_NOT_EXISTS;

@Service
public class PaymentService {
    private PaymentData paymentData;
    private ReservationService reservationService;
    private PricingService pricingService;

    @Autowired
    public PaymentService(PaymentData paymentData, ReservationService reservationService, PricingService pricingService) {
        this.paymentData = paymentData;
        this.reservationService = reservationService;
        this.pricingService = pricingService;
    }

    public void addPayment(@NonNull final Payment payment) {
        Reservation reservation = reservationService.getReservation(payment.getReservationId());
        if (!validatePayment(payment, reservation)) {
            reservation.markReservationCancelled();
            payment.markPaymentDeclined();
            return;
        }
        paymentData.getPaymentById().put(payment.getId(), payment);
        paymentData.getPaymentIdByReservationId().put(payment.getReservationId(), payment.getId());
        reservation.markReservationCompleted();
        payment.markPaymentApproved();
    }

    public Payment getPaymentById(@NonNull final String paymentId) {
        if (!paymentData.getPaymentById().containsKey(paymentId)) {
            throw new HotelBookingException(PAYMENT_NOT_EXISTS, "payment not exists");
        }
        return paymentData.getPaymentById().get(paymentId);
    }

    public Payment getPaymentByReservationId(@NonNull final String reservationId) {
        String paymentId = paymentData.getPaymentIdByReservationId().get(reservationId);
        return getPaymentById(paymentId);
    }

    private boolean validatePayment(Payment payment, Reservation reservation) {
        Bill bill = pricingService.getBill(reservation.getId());
        double amountPaid = payment.getAmountPaid().values().stream().mapToDouble(amount -> amount).sum();
        return BigDecimal.valueOf(amountPaid).compareTo(BigDecimal.valueOf(bill.getAmountToBePaid())) == 0;
    }
}

