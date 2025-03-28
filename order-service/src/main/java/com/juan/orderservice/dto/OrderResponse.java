package com.juan.orderservice.dto;

public record OrderResponse(
        Long id,
        String reference,
        Double totalPrice,
        String paymentMethod,
        String customerId
) {
}
