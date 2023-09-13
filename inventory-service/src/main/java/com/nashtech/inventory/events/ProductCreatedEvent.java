package com.nashtech.inventory.events;

import lombok.Data;

@Data
public class ProductCreatedEvent {

	private String productId;
	private String title;
	private Double basePrice;
	private Float tax;
	private Integer quantity;

}
