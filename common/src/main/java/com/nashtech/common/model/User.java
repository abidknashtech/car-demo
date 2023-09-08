package com.nashtech.common.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
    String firstName;
    String lastName;
    String userId;
    String address;
    PaymentDetails paymentDetails;
}