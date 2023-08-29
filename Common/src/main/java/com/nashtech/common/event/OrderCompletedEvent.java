package com.nashtech.common.event;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCompletedEvent {
    private String orderId;
    private OrderStatus orderStatus;
}
