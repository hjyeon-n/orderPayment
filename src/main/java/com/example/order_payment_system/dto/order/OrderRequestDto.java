package com.example.order_payment_system.dto.order;

import java.util.List;

public record OrderRequestDto(List<ItemRequestDto> items) {
}
