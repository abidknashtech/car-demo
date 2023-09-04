package com.nashtech.common.commands;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CreatedShipmentCommand {
    @TargetAggregateIdentifier
    String shipmentId;
    String orderId;
    ShipmentStatus shipmentStatus = ShipmentStatus.SHIPMENT_CREATED;
}
