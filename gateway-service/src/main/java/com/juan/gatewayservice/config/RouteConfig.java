package com.juan.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Customer Service
                .route("customer-service", r -> r
                        .path("/api/v1/customer/**")
                        .uri("lb://customer-service"))

                // Order Service
                .route("order-service", r -> r
                        .path("/api/v1/order/**")
                        .uri("lb://order-service"))

                // Order Lines Service (redirects to order-service)
                .route("order-lines-service", r -> r
                        .path("/api/v1/order-line/**")
                        .uri("lb://order-service"))

                // Product Service
                .route("product-service", r -> r
                        .path("/api/v1/product/**")
                        .uri("lb://product-service"))

                // Category Service (redirects to product-service)
                .route("category-service", r -> r
                        .path("/api/v1/category/**")
                        .uri("lb://product-service"))

                // Payment Service
                .route("payment-service", r -> r
                        .path("/api/v1/payment/**")
                        .uri("lb://payment-service"))

                .build();
    }
}