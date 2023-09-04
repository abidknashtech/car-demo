package com.nashtech.order.events;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderCreatedEvent {
    String orderId;
    String carId;
    Double price;
    Integer quantity;
    String userId;
    OrderStatus orderStatus;
}
