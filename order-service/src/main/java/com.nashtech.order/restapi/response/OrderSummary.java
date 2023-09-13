package com.nashtech.order.restapi.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSummary {
    private String orderId;
    private String orderStatus;
    private String message;

}