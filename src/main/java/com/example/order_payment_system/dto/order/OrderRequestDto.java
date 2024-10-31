package com.example.order_payment_system.dto.order;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    private String product;
    private int quantity;
}
