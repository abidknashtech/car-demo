package com.nashtech.inventory.events;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class LineItemAddedEvent {
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