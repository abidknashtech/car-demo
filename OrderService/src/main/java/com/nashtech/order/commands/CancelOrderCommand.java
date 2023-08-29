package com.nashtech.order.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import com.nashtech.common.utils.OrderStatus;

@Value
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;
    private OrderStatus orderStatus;
    private String reason;
}