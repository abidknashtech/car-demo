package com.nashtech.inventory.events;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class LineItemsEvent {
    String productId;
    String orderId;
    String userId;
    Integer quantity;
}
