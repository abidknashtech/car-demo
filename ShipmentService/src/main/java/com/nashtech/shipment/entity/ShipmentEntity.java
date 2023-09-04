package com.nashtech.shipment.entity;

import com.nashtech.common.model.ShipmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class ShipmentEntity {
    @Id
    @Column(unique = true)
    private String shipmentId;
    @Column(unique = true)
    private String orderId;
    private ShipmentStatus shipmentStatus;
}
