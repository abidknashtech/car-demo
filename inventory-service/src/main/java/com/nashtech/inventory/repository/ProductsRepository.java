package com.nashtech.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, String> {
	Product findByProductId(String productId);

}
