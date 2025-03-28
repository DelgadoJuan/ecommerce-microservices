package com.juan.orderservice.service;

import com.juan.orderservice.dto.OrderLineRequest;
import com.juan.orderservice.dto.OrderLineResponse;

import java.util.List;

public interface OrderLineService {
    Long saveOrderLine(OrderLineRequest orderLineRequest);
    List<OrderLineResponse> findAllByOrderId(Long orderId);
}
