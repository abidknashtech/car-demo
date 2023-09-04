package com.nashtech.payment.events;

import com.nashtech.common.events.PaymentProcessedEvent;
import com.nashtech.payment.data.Payment;
import com.nashtech.payment.data.PaymentsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class PaymentsEventHandler {

    private PaymentsRepository paymentsRepository;

    @EventHandler
    public void on(PaymentProcessedEvent event){

        Payment payment = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("Completed")
                .build();

        paymentsRepository.save(payment);
    }
}
