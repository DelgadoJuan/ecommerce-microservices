package com.juan.paymentservice.notification;

import com.juan.paymentservice.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerLastname,
        String customerEmail
) {
}
