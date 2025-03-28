package com.juan.productservice.service;

import com.juan.productservice.dto.CategoryRequest;

public interface CategoryService {
    Long createCategory(CategoryRequest categoryRequest);
}
