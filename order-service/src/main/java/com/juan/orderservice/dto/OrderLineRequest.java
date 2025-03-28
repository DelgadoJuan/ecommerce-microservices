package com.juan.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderLineRequest(
        Long id,
        Long orderId,
        @NotNull(message = "The product id must not be null") Long productId,
        @NotNull(message = "The quantity must not be null") @PositiveOrZero(message = "The quantity must be positive") int quantity) {
}
