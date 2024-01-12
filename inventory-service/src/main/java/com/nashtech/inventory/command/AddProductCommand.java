package com.nashtech.inventory.command;

import com.nashtech.common.model.LineItem;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class AddProductCommand {
    @TargetAggregateIdentifier
    String orderId;
    LineItem lineItem;
}
