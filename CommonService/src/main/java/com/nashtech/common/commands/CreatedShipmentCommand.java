package com.nashtech.common.commands;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreatedShipmentCommand {
    @TargetAggregateIdentifier
    private String shipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus = ShipmentStatus.CREATED;
}
