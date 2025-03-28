package com.juan.paymentservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerResponse(
        String customerId,
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "Lastname is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Email is invalid")
        String email
) {
}
