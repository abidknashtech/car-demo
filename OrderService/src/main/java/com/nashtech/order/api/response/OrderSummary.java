package com.nashtech.order.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.nashtech.common.utils.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummary {
	private  String orderId;
	private  OrderStatus orderStatus;
}