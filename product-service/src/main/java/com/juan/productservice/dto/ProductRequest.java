package com.juan.productservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
    @NotNull(message = "Product name is required")
    String name,
    @NotNull(message = "Product description is required")
    String description,
    @NotNull(message = "Product price is required")
    BigDecimal price,
    @Positive(message = "Product stock must be greater than zero")
    @NotNull(message = "Product stock is required")
    int stock,
    @NotNull(message = "Product category is required")
    @Positive(message = "Product category id must be greater than zero")
    Long categoryId
    ) {
}
