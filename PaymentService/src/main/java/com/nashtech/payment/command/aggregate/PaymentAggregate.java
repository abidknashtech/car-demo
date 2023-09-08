package com.nashtech.payment.command.aggregate;

import com.nashtech.common.command.ProcessPaymentCommand;

import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.common.event.PaymentCancelledEvent;
import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.User;
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
    private Double baseAmount;

    public PaymentAggregate() {
    }
    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {

        User user = getUser();

        if(!processPaymentCommand.getUserId().equals(user.getUserId())) {
            PaymentCancelledEvent paymentCancelledEvent = PaymentCancelledEvent.builder()
                    .paymentId(processPaymentCommand.getPaymentId())
                    .orderId(processPaymentCommand.getOrderId())
                    .userId(processPaymentCommand.getUserId())
                    .reason("User does not Exist").build();

            AggregateLifecycle.apply(paymentCancelledEvent);

            return;
        }

        Double subTotal = processPaymentCommand.getQuantity() * processPaymentCommand.getBaseAmount();
        Double grantTotal = subTotal + (processPaymentCommand.getQuantity() * processPaymentCommand.getTax());

        if(grantTotal >= user.getPaymentDetails().getBalanceAmount()){

            PaymentCancelledEvent paymentCancelledEvent = PaymentCancelledEvent.builder()
                    .paymentId(processPaymentCommand.getPaymentId())
                    .orderId(processPaymentCommand.getOrderId())
                    .userId(processPaymentCommand.getUserId())
                    .reason("Insufficient Amount").build();

            AggregateLifecycle.apply(paymentCancelledEvent);

            return;
        }

            PaymentApprovedEvent paymentApprovedEvent =
                    PaymentApprovedEvent.builder()
                            .orderId(processPaymentCommand.getOrderId())
                            .paymentId(processPaymentCommand.getPaymentId())
                            .productId(processPaymentCommand.getProductId())
                            .quantity(processPaymentCommand.getQuantity())
                            .userId(processPaymentCommand.getUserId())
                            .tax(processPaymentCommand.getTax())
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
        this.userId = paymentApprovedEvent.getUserId();
        this.paymentStatus = String.valueOf(paymentApprovedEvent.getPaymentStatus());
    }

    //Hard coded User details
    private User getUser() {
        return User.builder()
                .firstName("Abid")
                .lastName("Khan")
                .address("Noida")
                .userId("9768")
                .paymentDetails(getPaymentDetails())
                .build();
    }

    //Hard coded payment details
    private PaymentDetails getPaymentDetails(){

        return PaymentDetails.builder()
                .bank("SBI")
                .cardNumber("0900987654435443")
                .validUntilYear(2028)
                .validUntilMonth(6)
                .cvv(334)
                .balanceAmount(1000D)
                .build();
    }
}
