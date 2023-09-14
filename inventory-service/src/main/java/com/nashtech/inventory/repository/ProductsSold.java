package com.nashtech.inventory.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name="products_sold")
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSold  {
    @Id
    private String productId;
    private String orderId;
    private String userId;
    private Integer quantity;
    private String title;
    private Double baseAmount;
    private Float tax;

}
