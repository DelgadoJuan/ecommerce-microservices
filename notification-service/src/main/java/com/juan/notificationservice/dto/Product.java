package com.juan.notificationservice.dto;

import java.math.BigDecimal;

public record Product(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
