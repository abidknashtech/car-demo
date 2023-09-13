package com.nashtech.order.repository;

import com.nashtech.order.repository.entity.OrderLookup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLookupRepository extends JpaRepository<OrderLookup, String> {

}
