package com.nashtech.order.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    private String carId;
    private String userId;
    private Double price;
    private Integer quantity;
    private String orderStatus;
}