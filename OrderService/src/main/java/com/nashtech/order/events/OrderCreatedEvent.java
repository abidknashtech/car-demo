package com.nashtech.order.events;

import com.nashtech.common.model.PaymentDetails;
import lombok.*;
import com.nashtech.common.utils.OrderStatus;

@Data
@NoArgsConstructor(force = true)
public class OrderCreatedEvent {
    private  String orderId;
    private String carId;
    private Double price;
    private Integer quantity;
    private String userId;
    private OrderStatus orderStatus;
    private PaymentDetails paymentDetails;
}
