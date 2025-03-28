package com.juan.orderservice.dto;

public record OrderLineResponse(
        Long id,
        String productId,
        Integer quantity
) {
}
