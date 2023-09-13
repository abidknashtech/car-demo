package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductReservedEvent {
    String orderId;
    String userId;
    String productId;
    String title;
    Double baseAmount;
    Float tax;
    Integer quantity;

}