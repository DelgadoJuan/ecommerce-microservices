package com.juan.paymentservice.service;

import com.juan.paymentservice.dto.PaymentRequest;

public interface PaymentService {
    Long createPayment(PaymentRequest paymentRequest);
}
