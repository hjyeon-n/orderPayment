package com.example.order_payment_system.dto.order;

import com.example.order_payment_system.dto.product.ProductEventDto;

import java.util.List;

public record OrderEventDto(Long orderId,
                            List<ProductEventDto> products,
                            String status) {
}
