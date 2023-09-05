package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductReservedEvent {
    String productId;
    Double price;
    Integer quantity;
    String orderId;
    String userId;

}