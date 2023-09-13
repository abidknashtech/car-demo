package com.nashtech.order.commands;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Value
@Builder
public class RejectOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
    String userId;
    String productId;
    String paymentId;
    String shipmentId;
    Date timestamp = new Date();
    String reasonToFailed;
    String errorMessage;
    OrderStatus orderStatus;
}