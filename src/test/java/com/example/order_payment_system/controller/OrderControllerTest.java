package com.example.order_payment_system.controller;

import com.example.order_payment_system.dto.order.OrderEventDto;
import com.example.order_payment_system.dto.order.OrderRequestDto;
import com.example.order_payment_system.entity.Order;
import com.example.order_payment_system.repository.OrderRepository;
import com.example.order_payment_system.service.OrderProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @MockBean
    private OrderProducerService orderProducerService;

    @Test
    void createOrderTest() throws Exception {
        // given
        OrderRequestDto orderRequestDto = new OrderRequestDto("Test", 100);

        // when
        mockMvc.perform(post("/orders")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        Order savedOrder = orderRepository.findAll().get(0);
        assertThat(savedOrder.getProduct()).isEqualTo("Test");
        assertThat(savedOrder.getQuantity()).isEqualTo(100);

        verify(orderProducerService, times(1)).sendOrderEvent(any(OrderEventDto.class));
    }
}