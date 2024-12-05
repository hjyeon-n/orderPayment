package com.example.order_payment_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OrderPaymentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderPaymentSystemApplication.class, args);
	}

}
