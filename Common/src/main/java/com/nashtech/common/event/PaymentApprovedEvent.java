package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentApprovedEvent {
    String paymentId;
    String orderId;
    Double price;
    String userId;
    Integer quantity;
    String productId;

}