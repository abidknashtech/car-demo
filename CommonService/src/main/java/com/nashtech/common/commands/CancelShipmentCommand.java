package com.nashtech.common.commands;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CancelShipmentCommand {
    @TargetAggregateIdentifier
    private String shipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus = ShipmentStatus.CANCELLED;
}
