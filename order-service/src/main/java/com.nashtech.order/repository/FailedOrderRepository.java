package com.nashtech.order.repository;

import com.nashtech.order.repository.entity.FailedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedOrderRepository extends JpaRepository<FailedOrder, String> {
    FailedOrder findByOrderId(String orderId);
}
