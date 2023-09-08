package com.nashtech.order.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Date timestamp;
    private String reasonToRejectedOrder;

}