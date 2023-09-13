package com.nashtech.inventory.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, String> {

	Product findByProductId(String productId);
	Product findByProductIdOrTitle(String productId, String title);

}
