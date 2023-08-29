package com.nashtech.order.commands;

import com.nashtech.common.model.PaymentDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import com.nashtech.common.utils.OrderStatus;

@Builder
@Data
public class CreateOrderCommand {
        
    @TargetAggregateIdentifier
    private  String orderId;
    private String carId;
    private Double price;
    private Integer quantity;
    private String userId;
    private OrderStatus orderStatus;
    private final PaymentDetails paymentDetails;
}