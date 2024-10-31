package com.example.order_payment_system.service;

import com.example.order_payment_system.dto.order.OrderEventDto;
import com.example.order_payment_system.dto.order.OrderResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {
    private static final String TOPIC = "order-events";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderEvent(OrderEventDto orderEventDto) {
        try {
            String message = objectMapper.writeValueAsString(orderEventDto);
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
