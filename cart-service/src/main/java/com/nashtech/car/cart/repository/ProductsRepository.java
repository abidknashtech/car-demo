package com.nashtech.car.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
	ProductEntity findByProductId(String productId);

}
