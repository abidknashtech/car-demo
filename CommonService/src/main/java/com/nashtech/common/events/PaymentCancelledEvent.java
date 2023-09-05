package com.nashtech.common.events;

import com.nashtech.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentCancelledEvent {
     String paymentId;
     String orderId;
     PaymentStatus paymentStatus;
}
