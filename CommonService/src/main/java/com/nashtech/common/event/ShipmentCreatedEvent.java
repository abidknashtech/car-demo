package com.nashtech.common.event;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Data;

@Data
public class ShipmentCreatedEvent {
    private String shipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus;
}
