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
     PaymentStatus paymentStatus = PaymentStatus.PAYMENT_CANCELED;
}
