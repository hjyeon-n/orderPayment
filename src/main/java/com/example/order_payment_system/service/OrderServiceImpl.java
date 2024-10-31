package com.example.order_payment_system.service;

import com.example.order_payment_system.dto.order.OrderEventDto;
import com.example.order_payment_system.dto.order.OrderRequestDto;
import com.example.order_payment_system.dto.order.OrderResponseDto;
import com.example.order_payment_system.entity.Order;
import com.example.order_payment_system.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducerService orderProducerService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProducerService orderProducerService) {
        this.orderRepository = orderRepository;
        this.orderProducerService = orderProducerService;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .product(orderRequestDto.getProduct())
                .quantity(orderRequestDto.getQuantity())
                .build();

        orderRepository.save(order);

        OrderEventDto orderEventDto = new OrderEventDto(order.getOrderId(), order.getProduct(), order.getQuantity(), order.getStatus());

        orderProducerService.sendOrderEvent(orderEventDto);

        return OrderResponseDto.toDto(order);
    }
}
