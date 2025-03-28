package com.juan.productservice.service.Impl;

import com.juan.productservice.dto.CategoryRequest;
import com.juan.productservice.mapper.CategoryMapper;
import com.juan.productservice.repository.CategoryRepository;
import com.juan.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Long createCategory(CategoryRequest categoryRequest) {
        return categoryRepository.save(categoryMapper.toEntity(categoryRequest)).getId();
    }
}
