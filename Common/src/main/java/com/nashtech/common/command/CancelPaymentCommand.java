package com.nashtech.common.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CancelPaymentCommand {
    @TargetAggregateIdentifier
    String paymentId;
    String orderId;
    Integer quantity;
    String userId;
    String reasonToFailed;
    String productId;
    Double price;

}
