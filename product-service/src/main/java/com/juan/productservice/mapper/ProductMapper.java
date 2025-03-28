package com.juan.productservice.mapper;

import com.juan.productservice.dto.ProductPurchaseResponse;
import com.juan.productservice.dto.ProductRequest;
import com.juan.productservice.dto.ProductResponse;
import com.juan.productservice.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    ProductEntity toEntity(ProductRequest productRequest);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.description", target = "categoryDescription")
    ProductResponse toProductResponse(ProductEntity productEntity);

    @Mapping(source = "productEntity.id", target = "productId")
    @Mapping(target = "quantity", source = "quantity")
    ProductPurchaseResponse toProductPurchaseResponse(ProductEntity productEntity, int quantity);
}
