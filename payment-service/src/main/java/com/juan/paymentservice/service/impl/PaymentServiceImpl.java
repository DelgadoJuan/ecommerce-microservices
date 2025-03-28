package com.juan.paymentservice.service.impl;

import com.juan.paymentservice.notification.PaymentNotificationRequest;
import com.juan.paymentservice.dto.PaymentRequest;
import com.juan.paymentservice.mapper.PaymentMapper;
import com.juan.paymentservice.notification.NotificationProducer;
import com.juan.paymentservice.repository.PaymentRepository;
import com.juan.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Long createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPaymentEntity(paymentRequest));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                    paymentRequest.orderReference(),
                    paymentRequest.amount(),
                    paymentRequest.paymentMethod(),
                    paymentRequest.customer().name(),
                    paymentRequest.customer().lastName(),
                    paymentRequest.customer().email()
                )
        );

        return payment.getId();
    }
}
