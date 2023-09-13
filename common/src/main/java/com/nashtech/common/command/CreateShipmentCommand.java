package com.nashtech.common.command;

import com.nashtech.common.model.User;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CreateShipmentCommand {
    @TargetAggregateIdentifier
    String shipmentId;
    String paymentId;
    String orderId;
    User user;
    String productId;
    Double subTotal;
    Double total;
    Float tax;
    Double basePrice;
    Integer quantity;

}
