package com.juan.productservice.service.Impl;

import com.juan.productservice.dto.ProductRequest;
import com.juan.productservice.dto.ProductPurchaseRequest;
import com.juan.productservice.dto.ProductPurchaseResponse;
import com.juan.productservice.dto.ProductResponse;
import com.juan.productservice.entity.ProductEntity;
import com.juan.productservice.exception.ProductPurchaseException;
import com.juan.productservice.mapper.ProductMapper;
import com.juan.productservice.repository.CategoryRepository;
import com.juan.productservice.repository.ProductRepository;
import com.juan.productservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Long createProduct(@Valid ProductRequest productRequest) {
        if (!categoryRepository.existsById(productRequest.categoryId())) {
            throw new EntityNotFoundException("Category not exist");
        }
        return productRepository.save(productMapper.toEntity(productRequest)).getId();
    }

    @Override
    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequest) {
        return productPurchaseRequest.stream()
                .map(request -> {
                    ProductEntity product = productRepository.findById(request.productId())
                            .orElseThrow(() -> new ProductPurchaseException("Product not found"));

                    validateStock(product, request.quantity());
                    updateProductStock(product, request.quantity());

                    return productMapper.toProductPurchaseResponse(product, request.quantity());
                })
                .collect(Collectors.toList());
    }

    private void validateStock(ProductEntity product, int requestQuantity) {
        if (product.getStock() < requestQuantity) {
            throw new ProductPurchaseException("Insufficient stock for product: " + product.getId());
        }
    }

    private void updateProductStock(ProductEntity product, int quantity) {
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    @Override
    public ProductResponse findById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID: " + productId));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
