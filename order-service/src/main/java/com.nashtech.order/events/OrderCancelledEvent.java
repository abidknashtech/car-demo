package com.nashtech.order.events;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderCancelledEvent {
    String orderId;
    String productId;
    String paymentId;
    String shipmentId;
    String reasonToFailed;
    OrderStatus orderStatus;
}