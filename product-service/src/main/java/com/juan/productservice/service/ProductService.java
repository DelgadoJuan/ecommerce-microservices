package com.juan.productservice.service;

import com.juan.productservice.dto.ProductRequest;
import com.juan.productservice.dto.ProductPurchaseRequest;
import com.juan.productservice.dto.ProductPurchaseResponse;
import com.juan.productservice.dto.ProductResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    Long createProduct(@Valid ProductRequest productRequest);
    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequest);
    ProductResponse findById(Long productId);
    List<ProductResponse> findAll();
}
