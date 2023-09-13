package com.nashtech.order.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "failed_orders")
public class FailedOrder {
    @Id
    private String orderId;
    private String userId;
    private String productId;
    private String paymentId;
    private String shipmentId;
    private Date timestamp = new Date();
    private String reasonToFailed;
    private String orderStatus;
}
