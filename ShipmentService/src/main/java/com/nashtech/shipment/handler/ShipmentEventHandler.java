package com.nashtech.shipment.handler;

import com.nashtech.common.event.ShipmentCancelEvent;
import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.shipment.model.ShipmentModel;
import com.nashtech.shipment.repository.ShipmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventHandler {

    private final ShipmentRepository shipmentRepository;

    public ShipmentEventHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @EventHandler
    public void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        ShipmentModel shipmentModel = new ShipmentModel();
        BeanUtils.copyProperties(shipmentCreatedEvent, shipmentModel);
        shipmentRepository.save(shipmentModel);
    }

    @EventHandler
    public void on(ShipmentCancelEvent shipmentCancelEvent) {
        ShipmentModel shipmentModel = shipmentRepository.findByShipmentId(shipmentCancelEvent.getShipmentId());
        BeanUtils.copyProperties(shipmentCancelEvent, shipmentModel);
        shipmentRepository.save(shipmentModel);
    }
}
