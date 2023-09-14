package com.nashtech.common.event;

import com.nashtech.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentCancelledEvent {
    String paymentId;
    String orderId;
    Integer quantity;
    String userId;
    String reasonToFailed;
    String productId;
    PaymentStatus paymentStatus;

}
