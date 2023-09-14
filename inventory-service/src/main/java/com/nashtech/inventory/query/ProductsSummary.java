package com.nashtech.inventory.query;

import lombok.Data;

@Data
public class ProductsSummary {
    private String productId;
    private String title;
    private Double basePrice;
    private Float tax;
    private Integer quantity;

}
