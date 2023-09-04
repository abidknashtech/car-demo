package com.nashtech.common.event;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class ShipmentCancelEvent {
    String shipmentId;
    String orderId;
    ShipmentStatus shipmentStatus;
}
