package com.nashtech.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsSoldRepository extends JpaRepository<ProductsSold, String> {
    ProductsSold findByProductIdAndOrderId(String productId,String orderId);

}
