package com.nashtech.order.query;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FindOrderQuery {
    String orderId;

}