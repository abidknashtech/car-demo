package com.nashtech.shipment.entity;

import com.nashtech.common.model.ShipmentStatus;
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
    private String shipmentId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
    private String userId;
    private String paymentId;
    private ShipmentStatus shipmentStatus;
}
