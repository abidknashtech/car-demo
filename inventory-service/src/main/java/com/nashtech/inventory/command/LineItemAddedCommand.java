package com.nashtech.inventory.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

@Value
@Builder
public class LineItemAddedCommand {
    @TargetAggregateIdentifier
    String orderId;
    String userId;
    String productId;
    Integer quantity;
    String brand;
    String model;
    Integer year;
    String color;
    Double mileage;
    Double basePrice;
    Double subTotal;
    Double total;
    Float totalTax;
    Float tax;
    Map<Boolean,String> inStock;
}