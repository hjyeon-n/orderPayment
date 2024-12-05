package com.example.order_payment_system.dto.order;

import java.time.LocalDate;

public record OrderFilterDto(LocalDate startDate,
                             LocalDate endDate,
                             String status) {
}
