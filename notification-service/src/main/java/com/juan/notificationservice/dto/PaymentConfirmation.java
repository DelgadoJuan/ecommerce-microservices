package com.juan.notificationservice.dto;

import com.juan.notificationservice.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerLastname,
        String customerEmail
) {
}
