package com.example.order_payment_system.service;

import com.example.order_payment_system.dto.order.*;
import com.example.order_payment_system.entity.Order;
import com.example.order_payment_system.entity.OrderItem;
import com.example.order_payment_system.entity.Product;
import com.example.order_payment_system.repository.OrderRepository;
import com.example.order_payment_system.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProducerService orderProducerService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, OrderProducerService orderProducerService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProducerService = orderProducerService;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .status("PENDING")
                .orderDate(LocalDate.now())
                .build();

        for (ItemRequestDto itemDto : orderRequestDto.items()) {
            // Product 조회
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // OrderItem 생성
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemDto.quantity())
                    .build();

            order.addOrderItem(orderItem);
        }

        orderRepository.save(order);

        OrderEventDto orderEventDto = orderProducerService.toOrderEventDto(order);
        orderProducerService.sendOrderEvent(orderEventDto);

        return OrderResponseDto.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersByCriteria(OrderFilterDto orderFilterDto) {
        List<Order> orders =  orderRepository.findCriteria(
                orderFilterDto.startDate(),
                orderFilterDto.endDate(),
                orderFilterDto.status()
        );

        return orders.stream()
                .map(OrderResponseDto::toDto)
                .toList();
    }

    @Override
    @Cacheable(value = "orders", key = "#orderId")
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        return OrderResponseDto.toDto(order);
    }
 }
