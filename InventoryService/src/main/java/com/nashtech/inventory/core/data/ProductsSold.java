package com.nashtech.inventory.core.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name="products-sold")
@Data
public class ProductsSold  {

    @Id
    private String productId;
    private String title;
    private Double basePrice;
    private Float tax;
    private Integer quantity;
    private Double subTotal;
    private Double grantTotal;
    private String orderId;
    private String userId;

}
