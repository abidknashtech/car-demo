package com.nashtech.order.events;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderCancelledEvent {
    String orderId;
    OrderStatus orderStatus;
    String reason;

}