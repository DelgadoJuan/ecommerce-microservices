package com.juan.notificationservice.repository;

import com.juan.notificationservice.entity.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<NotificationEntity, String> {
    boolean existsByOrderConfirmationOrderReference(String orderReference);
    boolean existsByPaymentConfirmationOrderReference(String orderReference);
}
