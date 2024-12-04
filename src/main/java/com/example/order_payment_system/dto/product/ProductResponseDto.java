package com.example.order_payment_system.dto.product;

public record ProductResponseDto(Long productId,
                                 String name,
                                 double price,
                                 int quantity) {
}
