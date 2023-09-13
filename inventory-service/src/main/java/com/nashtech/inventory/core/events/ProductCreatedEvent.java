package com.nashtech.inventory.core.events;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductCreatedEvent {

	private String productId;
	private String title;
	private Double price;
	private Integer quantity;

}
