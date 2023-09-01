package com.nashtech.shipment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "shipment")
@Entity
public class ShipmentModel {
    @Id
    @Column(unique = true)
    private String shipmentId;
    @Column(unique = true)
    private String orderId;
    private String shipmentStatus;
}
