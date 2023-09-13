package com.nashtech.inventory.query.rest;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRestModel {
	private String productId;
	private String title;
	private Double price;
	private Integer quantity;
}
