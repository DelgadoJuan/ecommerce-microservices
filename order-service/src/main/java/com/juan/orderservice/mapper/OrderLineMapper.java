package com.juan.orderservice.mapper;

import com.juan.orderservice.dto.OrderLineRequest;
import com.juan.orderservice.dto.OrderLineResponse;
import com.juan.orderservice.entity.OrderLineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    @Mapping(source = "orderId", target = "order.id")
    OrderLineEntity toOrderLineEntity(OrderLineRequest orderLineRequest);
    OrderLineResponse toOrderLineResponse(OrderLineEntity orderLineEntity);
}
