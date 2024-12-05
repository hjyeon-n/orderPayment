package com.example.order_payment_system.dto.order;

import com.example.order_payment_system.dto.product.ProductResponseDto;
import com.example.order_payment_system.entity.Order;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record OrderResponseDto(Long orderId,
                               String status,
                               LocalDate orderDate,
                               List<ProductResponseDto> products) implements Serializable {

    public static OrderResponseDto toDto(Order order) {
        List<ProductResponseDto> productDtos = order.getOrderItems().stream()
                .map(orderItem -> new ProductResponseDto(
                        orderItem.getProduct().getProductId(),
                        orderItem.getProduct().getName(),
                        orderItem.getProduct().getPrice(),
                        orderItem.getQuantity()
                ))
                .toList();

        return new OrderResponseDto(
                order.getOrderId(),
                order.getStatus(),
                order.getOrderDate(),
                productDtos
        );
    }

}
