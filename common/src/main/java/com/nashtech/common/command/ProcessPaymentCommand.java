package com.nashtech.common.command;

import com.nashtech.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ProcessPaymentCommand {
    @TargetAggregateIdentifier
    String paymentId;
    String orderId;
    Integer quantity;
    Float tax;
    Double baseAmount;
    String productId;
    String userId;
    PaymentStatus paymentStatus = PaymentStatus.PAYMENT_APPROVED;


}