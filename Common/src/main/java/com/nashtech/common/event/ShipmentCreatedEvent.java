package com.nashtech.common.event;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShipmentCreatedEvent {
    String shipmentId;
    String orderId;
    String productId;
    Integer quantity;
    Double price;
    String userId;
    String paymentId;
    ShipmentStatus shipmentStatus;
}
