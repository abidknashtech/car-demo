package com.nashtech.common.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    String productId;
    String title;
    Double basePrice;
    Integer quantity;
    Float tax;
    String orderId;
    String userId;
}
