package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShipmentCancelledEvent {
    String orderId;
    String productId;
    Integer quantity;
    Double price;
    String userId;
    String reasonToFailed;
    String paymentId;
    String shipmentId;

}
