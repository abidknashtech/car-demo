package com.nashtech.order.repository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
	ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}
