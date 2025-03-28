package com.juan.productservice.controller;

import com.juan.productservice.dto.ProductRequest;
import com.juan.productservice.dto.ProductPurchaseRequest;
import com.juan.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseProducts(@RequestBody List<ProductPurchaseRequest> productPurchaseRequests) {
        return new ResponseEntity<>(productService.purchaseProducts(productPurchaseRequests), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable("productId") Long productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
