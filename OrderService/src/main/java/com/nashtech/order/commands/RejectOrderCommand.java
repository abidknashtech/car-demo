package com.nashtech.order.commands;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class RejectOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
    OrderStatus orderStatus;
    String reasonToReject;

}