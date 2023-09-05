package com.nashtech.payment.events;

import com.nashtech.common.events.PaymentCancelledEvent;
import com.nashtech.common.events.PaymentProcessedEvent;
import com.nashtech.payment.data.Payment;
import com.nashtech.payment.data.PaymentsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentsEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(PaymentsEventHandler.class);
    private PaymentsRepository paymentsRepository;

    @EventHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent){

        LOGGER.info("PaymentProcessedEvent is called for orderId: " + paymentProcessedEvent.getOrderId());
        Payment payment = Payment.builder()
                .paymentId(paymentProcessedEvent.getPaymentId())
                .orderId(paymentProcessedEvent.getOrderId())
                .paymentStatus(paymentProcessedEvent.getPaymentStatus())
                .build();

        paymentsRepository.save(payment);
    }
    @EventHandler
    public void on(PaymentCancelledEvent paymentCancelledEvent){

        Payment payment =paymentsRepository.findByOrderId(paymentCancelledEvent.getOrderId());
        if (payment == null){
            LOGGER.info("No Record found for the orderId:{}" + paymentCancelledEvent.getOrderId());
        } else {
            LOGGER.info("PaymentCancelledEvent is called for orderId: " + paymentCancelledEvent.getOrderId());

            payment.setPaymentStatus(paymentCancelledEvent.getPaymentStatus());
            paymentsRepository.save(payment);
        }
    }
}
