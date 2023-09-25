package com.nashtech.inventory.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name="products")
@Data
public class ProductEntity {
	@Id
	private String productId;
	private String brand;
	private String model;
	private Integer year;
	private String color;
	private Double mileage;
	private Double basePrice;
	private Integer quantity;
	private Float tax;
	private Date timestamp = new Date();

}
