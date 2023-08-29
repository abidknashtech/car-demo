package com.nashtech.order.events;

import lombok.*;
import utils.OrderStatus;

@Data
@NoArgsConstructor(force = true)
public class OrderCreatedEvent {
   // car details
    private  String orderId;
    private String carId;
    //private String brand;
    //private String model;
    //private String year;
    //private String color;
    //private Integer mileage;
    private Double price;

    // order details
    private Integer quantity;
    private String userId;
    private OrderStatus orderStatus;
}
