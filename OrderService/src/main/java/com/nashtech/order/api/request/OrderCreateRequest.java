package com.nashtech.order.api.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class OrderCreateRequest {

    @NotBlank(message = "Order carId is a required field")
    private String carId;

    @Min(value = 1, message = "Quantity cannot be lower than 1")
    private int quantity;

    @NotBlank(message = "UserId is a required field")
    private String userId;

    @DecimalMin(value = "1.0")
    private Double price;

}