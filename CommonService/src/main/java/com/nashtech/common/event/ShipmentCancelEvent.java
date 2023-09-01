package com.nashtech.common.event;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Data;

@Data
public class ShipmentCancelEvent {
    private String shipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus;
}
