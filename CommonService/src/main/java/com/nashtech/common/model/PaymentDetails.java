package com.nashtech.common.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentDetails {
    private String name;
    private String cardNumber;
    private Integer validUntilMonth;
    private Integer validUntilYear;
    private Integer cvv;
}
