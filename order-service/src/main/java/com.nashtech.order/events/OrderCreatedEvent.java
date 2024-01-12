package com.nashtech.order.events;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;

import java.util.HashMap;

@Value
@Builder
public class OrderCreatedEvent {
    String orderId;
    private HashMap<String,Integer> orderLines;
    //String productId;
    //Integer quantity;
    String userId;
    OrderStatus orderStatus;

}
