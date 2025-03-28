package com.juan.orderservice.service;

import com.juan.orderservice.dto.OrderRequest;
import com.juan.orderservice.dto.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Long createOrder(OrderRequest orderRequest);
    List<OrderResponse> findAll();
    OrderResponse findById(Long orderId);
}
