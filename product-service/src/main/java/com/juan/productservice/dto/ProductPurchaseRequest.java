package com.juan.productservice.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
    @NotNull(message = "Product id is required")
    Long productId,
    @NotNull(message = "Product quantity is required")
    int quantity
) {
}
