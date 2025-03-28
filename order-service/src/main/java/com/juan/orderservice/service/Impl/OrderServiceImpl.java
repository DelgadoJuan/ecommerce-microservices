package com.juan.orderservice.service.Impl;

import com.juan.orderservice.dto.*;
import com.juan.orderservice.exception.BusinessException;
import com.juan.orderservice.feign.CustomerClient;
import com.juan.orderservice.feign.PaymentClient;
import com.juan.orderservice.feign.ProductClient;
import com.juan.orderservice.kafka.OrderConfirmation;
import com.juan.orderservice.kafka.OrderProducer;
import com.juan.orderservice.mapper.OrderMapper;
import com.juan.orderservice.repository.OrderRepository;
import com.juan.orderservice.service.OrderLineService;
import com.juan.orderservice.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Override
    public Long createOrder(OrderRequest orderRequest) {
        // check the customer --> customer-service
        /*var customer = customerClient.getCustomerById(Long.valueOf(orderRequest.customerId()))
                .orElseThrow(() -> new BusinessException("Cannot create order:: customer not found")); */

        CompletableFuture<CustomerResponse> customerFuture = CompletableFuture.supplyAsync(() ->
                customerClient.getCustomerById(Long.valueOf(orderRequest.customerId()))
                        .orElseThrow(() -> new BusinessException("Cannot create order:: customer not found"))
        );

        //purchase the products --> product-service
        //List<PurchaseResponse> productResponse = productClient.purchase(orderRequest.purchasedProducts());

        CompletableFuture<List<PurchaseResponse>> productFuture = CompletableFuture.supplyAsync(() ->
                productClient.purchase(orderRequest.purchasedProducts())
        );

        // Esperar futures antes de continuar
        CompletableFuture.allOf(customerFuture, productFuture).join();

        CustomerResponse customerResponse = customerFuture.join();
        List<PurchaseResponse> productResponse = productFuture.join();

        // persist order
        var order = orderRepository.save(orderMapper.toEntity(orderRequest));

        // persist order lines
        for (PurchaseRequest purchaseRequest : orderRequest.purchasedProducts()) {
            // persist order line
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        /*CustomerResponse customerResponse = new CustomerResponse(
                customer.customerId(),
                customer.name(),
                customer.lastName(),
                customer.email()
        );*/

        var paymentRequest = new PaymentRequest(
                orderRequest.totalPrice(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customerResponse
        );

        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation --> notification-service (kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.totalPrice(),
                orderRequest.paymentMethod(),
                customerResponse,
                productResponse
        ));

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with the id " + orderId));
    }
}
