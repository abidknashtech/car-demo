package com.nashtech.inventory.restapi;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ProductRequest {
	@NotBlank(message="Product title is a required field")
	private String title;
	@Min(value=1, message="Quantity cannot be lower than 1")
	private Double price;
	@Min(value=1, message="Price cannot be lower than 1")
	private Integer quantity;
	private Float tax;
}
