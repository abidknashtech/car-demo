package com.nashtech.payment.handler;

import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.payment.data.Payment;
import com.nashtech.payment.data.PaymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentsEventHandler {
    private PaymentsRepository paymentsRepository;

    public PaymentsEventHandler(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @EventHandler
    public void on(PaymentApprovedEvent paymentApprovedEvent){
        log.info("PaymentProcessedEvent is called for orderId:{} ", paymentApprovedEvent.getOrderId());
        Payment payment = Payment.builder()
                .paymentId(paymentApprovedEvent.getPaymentId())
                .orderId(paymentApprovedEvent.getOrderId())
                .productId(paymentApprovedEvent.getProductId())
                .quantity(String.valueOf(paymentApprovedEvent.getQuantity()))
                .baseAmount(paymentApprovedEvent.getBasePrice())
                .subTotal(paymentApprovedEvent.getSubTotal())
                .tax(paymentApprovedEvent.getTax())
                .total(paymentApprovedEvent.getTotal())
                .build();

        paymentsRepository.save(payment);
    }

}
