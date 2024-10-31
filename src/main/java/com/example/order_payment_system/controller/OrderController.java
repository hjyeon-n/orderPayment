package com.example.order_payment_system.controller;

import com.example.order_payment_system.dto.order.OrderRequestDto;
import com.example.order_payment_system.dto.order.OrderResponseDto;
import com.example.order_payment_system.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrders(OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderResponseDto);
    }
}
