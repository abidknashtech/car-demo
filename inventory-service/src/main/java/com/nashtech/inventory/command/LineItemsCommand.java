package com.nashtech.inventory.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class LineItemsCommand {
    @TargetAggregateIdentifier
    String productId;
    String orderId;
    String userId;
    Integer quantity;
}
