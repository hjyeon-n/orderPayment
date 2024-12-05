package com.example.order_payment_system.service;

import com.example.order_payment_system.dto.order.OrderFilterDto;
import com.example.order_payment_system.dto.order.OrderRequestDto;
import com.example.order_payment_system.dto.order.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getOrdersByCriteria(OrderFilterDto orderFilterDto);

    OrderResponseDto getOrderById(Long orderId);
}
