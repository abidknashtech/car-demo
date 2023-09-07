package com.nashtech.payment.command.aggregate;

import com.nashtech.common.command.ProcessPaymentCommand;

import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.common.event.PaymentCancelledEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
    private String userId;
    private String reasonToFailed;
    private String paymentStatus;
    private String reason;

    public PaymentAggregate() {
    }
    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {

        if(processPaymentCommand.getPrice() <= 0) {

            PaymentCancelledEvent paymentCancelledEvent = PaymentCancelledEvent.builder()
                    .paymentId(processPaymentCommand.getPaymentId())
                    .orderId(processPaymentCommand.getOrderId())
                    .userId(processPaymentCommand.getUserDetails().getUserId())
                    .reason("Insufficient Amount").build();

            AggregateLifecycle.apply(paymentCancelledEvent);

            return;
        }

            PaymentApprovedEvent paymentApprovedEvent =
                    PaymentApprovedEvent.builder()
                            .orderId(processPaymentCommand.getOrderId())
                            .paymentId(processPaymentCommand.getPaymentId())
                            .productId(processPaymentCommand.getProductId())
                            .price(processPaymentCommand.getPrice())
                            .quantity(processPaymentCommand.getQuantity())
                            .paymentStatus(processPaymentCommand.getPaymentStatus())
                            .build();

            AggregateLifecycle.apply(paymentApprovedEvent);
        }

    @EventSourcingHandler
    public void on(PaymentApprovedEvent paymentApprovedEvent){
        this.paymentId = paymentApprovedEvent.getPaymentId();
        this.orderId = paymentApprovedEvent.getOrderId();
        this.productId = paymentApprovedEvent.getProductId();
        this.quantity = paymentApprovedEvent.getQuantity();
        this.price = paymentApprovedEvent.getPrice();
        this.userId = paymentApprovedEvent.getUserId();
        this.paymentStatus = String.valueOf(paymentApprovedEvent.getPaymentStatus());
    }

}
