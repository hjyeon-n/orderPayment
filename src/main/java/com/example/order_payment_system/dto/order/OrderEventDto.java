package com.example.order_payment_system.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEventDto {
    private Long orderId;
    private String product;
    private int quantity;
    private String state;
}
