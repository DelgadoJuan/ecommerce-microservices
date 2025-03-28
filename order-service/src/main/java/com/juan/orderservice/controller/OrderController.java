package com.juan.orderservice.controller;

import com.juan.orderservice.dto.OrderRequest;
import com.juan.orderservice.repository.OrderRepository;
import com.juan.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findById(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
    }
}
