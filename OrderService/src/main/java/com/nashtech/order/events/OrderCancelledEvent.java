package com.nashtech.order.events;

import lombok.Data;
import com.nashtech.common.utils.OrderStatus;

@Data
public class OrderCancelledEvent {
    private String orderId;
    private OrderStatus orderStatus;
    private String reason;
}