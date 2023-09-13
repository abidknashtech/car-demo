package com.nashtech.payment.events.handler;

import com.nashtech.common.event.PaymentApprovedEvent;
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

    public PaymentsEventHandler(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @EventHandler
    public void on(PaymentApprovedEvent paymentApprovedEvent){

        LOGGER.info("PaymentProcessedEvent is called for orderId:{} ", paymentApprovedEvent.getOrderId());
        Payment payment = Payment.builder()
                .paymentId(paymentApprovedEvent.getPaymentId())
                .orderId(paymentApprovedEvent.getOrderId())
                .productId(paymentApprovedEvent.getProductId())
                .quantity(String.valueOf(paymentApprovedEvent.getQuantity()))
                .subTotal(paymentApprovedEvent.getSubTotal())
                .tax(paymentApprovedEvent.getTax())
                .grantTotal(paymentApprovedEvent.getGrantTotal())
                .paymentStatus(paymentApprovedEvent.getPaymentStatus())
                .build();

        paymentsRepository.save(payment);
    }
}
