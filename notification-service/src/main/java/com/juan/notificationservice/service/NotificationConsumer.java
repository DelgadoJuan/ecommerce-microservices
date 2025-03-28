package com.juan.notificationservice.service;

import com.juan.notificationservice.dto.OrderConfirmation;
import com.juan.notificationservice.dto.PaymentConfirmation;
import jakarta.mail.MessagingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

public interface NotificationConsumer {
    @KafkaListener(topics = "payment-topic")
    void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException;

    @KafkaListener(topics = "order-topic")
    void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation
    ) throws MessagingException;
}
