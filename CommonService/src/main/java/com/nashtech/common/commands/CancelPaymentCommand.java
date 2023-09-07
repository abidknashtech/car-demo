package com.nashtech.common.commands;

import com.nashtech.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CancelPaymentCommand {

    @TargetAggregateIdentifier
     String paymentId;
     String orderId;
     Double price;
     Integer quantity;
     String userId;
     String reasonToFailed;
     String productId;
     PaymentStatus paymentStatus = PaymentStatus.PAYMENT_CANCELED;
}
