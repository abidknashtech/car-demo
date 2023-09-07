package com.nashtech.payment.command;

import com.nashtech.common.commands.CancelPaymentCommand;
import com.nashtech.common.commands.ProcessPaymentCommand;
import com.nashtech.common.events.PaymentCancelledEvent;
import com.nashtech.common.events.PaymentProcessedEvent;
import com.nashtech.common.model.PaymentStatus;
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

            PaymentProcessedEvent paymentProcessedEvent =
                    PaymentProcessedEvent.builder()
                            .orderId(processPaymentCommand.getOrderId())
                            .paymentId(processPaymentCommand.getPaymentId())
                            .productId(processPaymentCommand.getProductId())
                            .price(processPaymentCommand.getPrice())
                            .quantity(processPaymentCommand.getQuantity())
                            .paymentStatus(processPaymentCommand.getPaymentStatus())
                            .build();

            AggregateLifecycle.apply(paymentProcessedEvent);
        }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent){
        this.paymentId = paymentProcessedEvent.getPaymentId();
        this.orderId = paymentProcessedEvent.getOrderId();
        this.productId = paymentProcessedEvent.getProductId();
        this.quantity = paymentProcessedEvent.getQuantity();
        this.price = paymentProcessedEvent.getPrice();
        this.userId = paymentProcessedEvent.getUserId();
        this.paymentStatus = String.valueOf(paymentProcessedEvent.getPaymentStatus());
    }

}
