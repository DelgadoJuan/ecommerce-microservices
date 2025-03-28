package com.juan.orderservice.dto;

import java.math.BigDecimal;

public record PurchaseResponse(
    Long productId,
    String name,
    String description,
    BigDecimal price,
    int quantity) {
}
