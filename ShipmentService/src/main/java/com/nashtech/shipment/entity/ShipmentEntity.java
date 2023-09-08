package com.nashtech.shipment.entity;

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
    private Double subTotal;
    private Double grandTotal;
    private Float tax;
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String paymentId;
}
