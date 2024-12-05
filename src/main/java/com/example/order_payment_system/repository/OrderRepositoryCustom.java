package com.example.order_payment_system.repository;

import com.example.order_payment_system.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findCriteria(LocalDate startDate, LocalDate endDate, String status);
}
