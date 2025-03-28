package com.juan.productservice.mapper;

import com.juan.productservice.dto.CategoryRequest;
import com.juan.productservice.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity toEntity(CategoryRequest categoryRequest);
}
