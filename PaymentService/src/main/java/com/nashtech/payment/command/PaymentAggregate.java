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
    private String paymentStatus;

    public PaymentAggregate() {
    }
    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {
        //validate the payment details
        //publish the payment process event

        if(processPaymentCommand.getPaymentDetails() == null) {
            throw new IllegalArgumentException("Missing payment details");
        }

        if(processPaymentCommand.getOrderId() == null) {
            throw new IllegalArgumentException("Missing orderId");
        }

        if(processPaymentCommand.getPaymentId() == null) {
            throw new IllegalArgumentException("Missing paymentId");
        }

        log.info("Executing ProcessPaymentCommand for " +
                        "Order Id: {} and Payment Id: {}",
                processPaymentCommand.getOrderId(),
                processPaymentCommand.getPaymentId());

        PaymentProcessedEvent paymentProcessedEvent =
                PaymentProcessedEvent.builder()
                        .orderId(processPaymentCommand.getOrderId())
                        .paymentId(processPaymentCommand.getPaymentId())
                        .paymentStatus(processPaymentCommand.getPaymentStatus())
                        .build();

        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("PaymentProcessedEvent Applied");
    }

    @CommandHandler
    public void handle(CancelPaymentCommand cancelPaymentCommand) {
        if (PaymentStatus.PAYMENT_CANCELED.equals(cancelPaymentCommand.getPaymentStatus())) {

            PaymentCancelledEvent paymentCancelledEvent =
                    PaymentCancelledEvent.builder()
                            .orderId(cancelPaymentCommand.getOrderId())
                            .paymentId(cancelPaymentCommand.getPaymentId())
                            .paymentStatus(cancelPaymentCommand.getPaymentStatus())
                            .build();

            AggregateLifecycle.apply(paymentCancelledEvent);
        }

    }



    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent){
        this.paymentId = paymentProcessedEvent.getPaymentId();
        this.orderId = paymentProcessedEvent.getOrderId();
        this.paymentStatus = String.valueOf(paymentProcessedEvent.getPaymentStatus());
    }

    @EventSourcingHandler
    public void on(PaymentCancelledEvent paymentCancelledEvent) {
        this.paymentId = paymentCancelledEvent.getPaymentId();
        this.orderId = paymentCancelledEvent.getOrderId();
        this.paymentStatus = String.valueOf(paymentCancelledEvent.getPaymentStatus());
    }

}
