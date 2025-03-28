package com.juan.notificationservice.service.impl;

import com.juan.notificationservice.dto.OrderConfirmation;
import com.juan.notificationservice.dto.PaymentConfirmation;
import com.juan.notificationservice.entity.NotificationEntity;
import com.juan.notificationservice.enums.NotificationType;
import com.juan.notificationservice.repository.NotificationRepository;
import com.juan.notificationservice.service.EmailService;
import com.juan.notificationservice.service.NotificationConsumer;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumerImpl implements NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    @Override
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        if (!notificationRepository.existsByPaymentConfirmationOrderReference(paymentConfirmation.orderReference())) {
            log.info("Consuming payment success notification: {}", paymentConfirmation);
            notificationRepository.save(NotificationEntity
                    .builder()
                    .type(NotificationType.PAYMENT_CONFIRMATION)
                    .notificationDate(LocalDateTime.now())
                    .paymentConfirmation(paymentConfirmation)
                    .build()
            );

            // send email
            String customerFullName = paymentConfirmation.customerName() + " " + paymentConfirmation.customerLastname();
            emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(), customerFullName,
                    paymentConfirmation.amount(), paymentConfirmation.orderReference());
        }
    }

    @KafkaListener(topics = "order-topic")
    @Override
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        // Agregar verificación de mensaje único
        if (!notificationRepository.existsByOrderConfirmationOrderReference(orderConfirmation.orderReference())) {
            log.info("Consuming order confirmation notification: {}", orderConfirmation);

            notificationRepository.save(NotificationEntity
                    .builder()
                    .type(NotificationType.ORDER_CONFIRMATION)
                    .notificationDate(LocalDateTime.now())
                    .orderConfirmation(orderConfirmation)
                    .build()
            );

            var customerName = orderConfirmation.customerResponse().name() + " " + orderConfirmation.customerResponse().lastName();
            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.customerResponse().email(),
                    customerName,
                    orderConfirmation.totalAmount(),
                    orderConfirmation.orderReference(),
                    orderConfirmation.products()
            );
        }
    }
}
