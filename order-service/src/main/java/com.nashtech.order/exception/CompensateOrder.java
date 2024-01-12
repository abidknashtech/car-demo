package com.nashtech.order.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CompensateOrder {
    private String orderId;
    private String userId;
    private Set<String> productId;
    private String paymentId;
    private String shipmentId;
    private String reasonToFailed;
}
