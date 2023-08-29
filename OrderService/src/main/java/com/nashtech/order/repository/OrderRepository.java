package com.nashtech.order.repository;

import com.nashtech.order.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}
