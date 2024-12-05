package com.example.order_payment_system.dto.product;

import java.io.Serializable;

public record ProductResponseDto(Long productId,
                                 String name,
                                 double price,
                                 int quantity) implements Serializable  {
}
