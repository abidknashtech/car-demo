package com.nashtech.common.command;

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
    Double basePrice;
    String productId;
    String userId;

}