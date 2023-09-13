package com.nashtech.common.event;

import com.nashtech.common.model.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentApprovedEvent {
    String paymentId;
    String orderId;
    String productId;
    User user;
    Integer quantity;
    Double subTotal;
    Double total;
    Float tax;
    Double basePrice;

}