package com.juan.orderservice.kafka;

import com.juan.orderservice.dto.CustomerResponse;
import com.juan.orderservice.dto.PurchaseResponse;
import com.juan.orderservice.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
