package com.example.order_payment_system.repository;

import com.example.order_payment_system.entity.Order;
import com.example.order_payment_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
