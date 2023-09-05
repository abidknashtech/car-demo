package com.nashtech.common.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CancelShipmentCommand {
    @TargetAggregateIdentifier
    String orderId;
    String productId;
    Integer quantity;
    String userId;
    String reasonToFailed;

}
