package com.nashtech.payment.command;

import com.nashtech.common.commands.ProcessPaymentCommand;
import com.nashtech.common.commands.ValidatePaymentCommand;
import com.nashtech.common.events.PaymentProcessedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@Aggregate
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    @Autowired
    private CommandGateway commandGateway;

    private String orderStatus;

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

        log.info("Executing ValidatePaymentCommand for " +
                        "Order Id: {} and Payment Id: {}",
                processPaymentCommand.getOrderId(),
                processPaymentCommand.getPaymentId());

        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent(
                processPaymentCommand.getPaymentId(), processPaymentCommand.getOrderId()
        );

        AggregateLifecycle.apply(paymentProcessedEvent);


        log.info("PaymentProcessedEvent Applied");
    }

    @CommandHandler
    public void handle(ValidatePaymentCommand validatePaymentCommand) {
        // Create a new ValidatePaymentCommand object
        ValidatePaymentCommand newValidatePaymentCommand = ValidatePaymentCommand.builder()
                .orderId(validatePaymentCommand.getOrderId())
                .paymentId(UUID.randomUUID().toString())
                .orderStatus(validatePaymentCommand.getOrderStatus())
                .build();

        commandGateway.send(newValidatePaymentCommand, (commandMessage, commandResultMessage) -> {
            // Handle the result as needed
        });
    }



    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent){
        this.paymentId = paymentProcessedEvent.getPaymentId();
        this.orderId = paymentProcessedEvent.getOrderId();
    }

    @EventSourcingHandler
    public void on(ValidatePaymentCommand validatePaymentCommand){
        this.paymentId = validatePaymentCommand.getPaymentId();
        this.orderId = validatePaymentCommand.getOrderId();
        this.orderStatus=validatePaymentCommand.getOrderStatus();

    }
}
