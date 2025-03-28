package com.juan.orderservice.service.Impl;

import com.juan.orderservice.dto.OrderLineRequest;
import com.juan.orderservice.dto.OrderLineResponse;
import com.juan.orderservice.mapper.OrderLineMapper;
import com.juan.orderservice.repository.OrderLineRepository;
import com.juan.orderservice.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    @Override
    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLineEntity(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    @Override
    public List<OrderLineResponse> findAllByOrderId(Long orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
