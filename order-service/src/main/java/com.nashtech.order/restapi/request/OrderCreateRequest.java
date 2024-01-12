package com.nashtech.order.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty
    private HashMap<String,Integer> orderLines;

    //@NotBlank(message = "ProductId is a required field")
   // private String productId;

    //@Min(value = 1, message = "Quantity cannot be lower than 1")
    //private Integer quantity;

    @NotBlank(message = "UserId is a required field")
    private String userId;

}