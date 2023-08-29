package com.nashtech.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDetails {
    private final String bank;
    private final String cardNumber;
    private final int validUntilMonth;
    private final int validUntilYear;
    private final int cvv;
}