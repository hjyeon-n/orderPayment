package com.example.order_payment_system.dto.order;

import com.example.order_payment_system.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long orderId;
    private String product;
    private int quantity;
    private String state;

    public static OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .state(order.getStatus())
                .build();

        return orderResponseDto;
    }
}
