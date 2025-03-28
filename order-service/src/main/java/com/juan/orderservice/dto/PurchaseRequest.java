package com.juan.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "The product id must not be null")
        Long productId,
        @NotNull(message = "The quantity must not be null")
        @Positive(message = "The quantity must be positive")
        int quantity
) {
}
