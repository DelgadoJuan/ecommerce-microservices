package com.juan.orderservice.feign;

import com.juan.orderservice.dto.PurchaseRequest;
import com.juan.orderservice.dto.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {
    @PostMapping("/purchase")
    List<PurchaseResponse> purchase(@RequestBody List<PurchaseRequest> productResponse);
}