package com.juan.orderservice.dto;

public record CustomerResponse(
        String customerId,
        String name,
        String lastName,
        String email
) {
}
