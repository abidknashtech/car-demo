package com.nashtech.common.command;

import com.nashtech.common.model.LineItem;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

@Value
@Builder
public class ProcessPaymentCommand {
    @TargetAggregateIdentifier
    String paymentId;
    String orderId;
    String userId;
    Map<String, LineItem> orderLines;

}