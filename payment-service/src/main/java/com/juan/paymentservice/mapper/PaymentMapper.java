package com.juan.paymentservice.mapper;


import com.juan.paymentservice.dto.PaymentRequest;
import com.juan.paymentservice.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEntity toPaymentEntity(PaymentRequest paymentRequest);
}
