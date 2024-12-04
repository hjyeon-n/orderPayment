package com.example.order_payment_system.dto.product;

public record ProductEventDto(Long productId,
                              String name,
                              double price) {
}
