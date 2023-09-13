package com.nashtech.order.events;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderApprovedEvent {
    String orderId;
    String paymentId;
    String shipmentId;
    OrderStatus orderStatus;
}
