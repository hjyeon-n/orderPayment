package com.example.order_payment_system.controller;

import com.example.order_payment_system.dto.order.ItemRequestDto;
import com.example.order_payment_system.dto.order.OrderEventDto;
import com.example.order_payment_system.dto.order.OrderRequestDto;
import com.example.order_payment_system.dto.order.OrderResponseDto;
import com.example.order_payment_system.dto.product.ProductResponseDto;
import com.example.order_payment_system.entity.Order;
import com.example.order_payment_system.repository.OrderRepository;
import com.example.order_payment_system.repository.ProductRepository;
import com.example.order_payment_system.service.OrderProducerService;
import com.example.order_payment_system.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    // 주문 생성 테스트
    @Test
    void createOrderTest() throws Exception {
        // given
        OrderRequestDto orderRequestDto = new OrderRequestDto(List.of(
                new ItemRequestDto(1L, 2),
                new ItemRequestDto(2L, 5)
        ));

        OrderResponseDto responseDto = new OrderResponseDto(
                1L, "PENDING", LocalDate.now(), List.of(
                new ProductResponseDto(1L, "Product A", 100.0, 2),
                new ProductResponseDto(2L, "Product B", 50.0, 3)
        ));

        Mockito.when(orderService.createOrder(any(OrderRequestDto.class))).thenReturn(responseDto);

        // when
        mockMvc.perform(post("/orders")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        verify(orderService, times(1)).createOrder(any(OrderRequestDto.class));
    }
}