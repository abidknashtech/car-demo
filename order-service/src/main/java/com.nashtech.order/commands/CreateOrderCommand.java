package com.nashtech.order.commands;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.HashMap;

@Value
@Builder
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
  //  String productId;
   // Integer quantity;
    private HashMap<String,Integer> orderLines;
    String userId;

}