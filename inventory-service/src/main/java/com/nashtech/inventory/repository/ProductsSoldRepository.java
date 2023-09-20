package com.nashtech.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsSoldRepository extends JpaRepository<ProductsSoldEntity, String> {
    ProductsSoldEntity findByProductIdAndOrderId(String productId, String orderId);

}
