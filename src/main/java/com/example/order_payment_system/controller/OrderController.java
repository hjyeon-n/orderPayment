package com.example.order_payment_system.controller;

import com.example.order_payment_system.dto.order.OrderRequestDto;
import com.example.order_payment_system.dto.order.OrderResponseDto;
import com.example.order_payment_system.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 생성
    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrders(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderResponseDto);
    }

    // 주문 리스트 조회
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrders(@RequestBody OrderRequestDto orderRequestDto) {
        List<OrderResponseDto> orders = orderService.getOrders(orderRequestDto);
        return ResponseEntity.ok(orders);
    }

    // 단일 주문 조회
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        OrderResponseDto order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
