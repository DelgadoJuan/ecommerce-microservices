package com.juan.customerservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddressNotBelongingToCustomerException extends RuntimeException {
    private final String msg;
}
