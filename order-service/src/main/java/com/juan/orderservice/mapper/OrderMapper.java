package com.juan.orderservice.mapper;

import com.juan.orderservice.dto.OrderRequest;
import com.juan.orderservice.dto.OrderResponse;
import com.juan.orderservice.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "id", target = "id")
    OrderEntity toEntity(OrderRequest orderRequest);
    OrderResponse toDto(OrderEntity orderEntity);
}
