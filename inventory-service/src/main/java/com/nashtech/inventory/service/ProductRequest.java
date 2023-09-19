package com.nashtech.inventory.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {
	private String title;
	private Double price;
	private Integer quantity;
	private Float tax;

}
