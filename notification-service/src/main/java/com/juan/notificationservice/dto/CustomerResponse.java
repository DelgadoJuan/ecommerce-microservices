package com.juan.notificationservice.dto;

public record CustomerResponse(
        String customerId,
        String name,
        String lastName,
        String email
) {
}
