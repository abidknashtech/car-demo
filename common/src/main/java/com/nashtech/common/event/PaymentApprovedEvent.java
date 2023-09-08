package com.nashtech.common.event;

import com.nashtech.common.model.PaymentStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentApprovedEvent {
    String paymentId;
    String orderId;
    Double subTotal;
    Double grantTotal;
    Float tax;
    Integer quantity;
    String productId;
    String userId;
    String firstName;
    String lastName;
    String address;
    PaymentStatus paymentStatus;
}