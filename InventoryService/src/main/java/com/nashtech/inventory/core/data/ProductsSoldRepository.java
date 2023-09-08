package com.nashtech.inventory.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsSoldRepository extends JpaRepository<ProductsSold, String> {
    ProductsSold findByProductId(String productId);
}
