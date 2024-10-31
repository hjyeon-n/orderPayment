package com.example.order_payment_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long orderId;
    private String product;
    private int quantity;
    private String status = "PENDING";

    @Builder
    public Order(Long orderId, String product, int quantity, String status) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }
}
