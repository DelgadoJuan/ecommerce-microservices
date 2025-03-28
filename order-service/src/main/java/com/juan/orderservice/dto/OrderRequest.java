package com.juan.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.juan.orderservice.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(NON_EMPTY)
public record OrderRequest(
        Long id,
        @NotNull(message = "The payment method must not be null")
        PaymentMethod paymentMethod,
        String reference,
        @Positive(message = "The price must be positive")
        BigDecimal totalPrice,
        @NotEmpty(message = "The purchased products must not be empty")
        List<PurchaseRequest> purchasedProducts,
        @NotNull(message = "The customer id must not be null")
        String customerId
) {
}
