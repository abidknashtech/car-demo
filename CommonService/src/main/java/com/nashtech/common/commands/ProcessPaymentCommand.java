package com.nashtech.common.commands;

import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ProcessPaymentCommand {

    @TargetAggregateIdentifier
     String paymentId;
     String orderId;
     PaymentDetails paymentDetails;
     PaymentStatus paymentStatus = PaymentStatus.PAYMENT_APPROVED;


}
