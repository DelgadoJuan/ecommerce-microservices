package com.juan.productservice.dto;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        int stock,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
