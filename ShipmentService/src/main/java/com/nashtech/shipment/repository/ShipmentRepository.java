package com.nashtech.shipment.repository;

import com.nashtech.shipment.model.ShipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<ShipmentModel, String> {
    ShipmentModel findByShipmentId(String shipmentId);
}
