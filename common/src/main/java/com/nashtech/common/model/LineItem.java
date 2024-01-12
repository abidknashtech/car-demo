package com.nashtech.common.model;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class LineItem {
    private String productId;
    private Integer quantity;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private Double mileage;
    private Double basePrice;
    private Double subTotal;
    private Double total;
    private Float totalTax;
    private Float tax;
    private Map<Boolean,String> inStock;
}
