package com.juan.customerservice.service;

import com.juan.customerservice.dto.CustomerDTO;
import com.juan.customerservice.dto.CustomerListDTO;

import java.util.List;

public interface CustomerService {
    void deleteById(Long id);
    void createCustomer(CustomerDTO customerDTO);
    void updateCustomer(Long id, CustomerDTO customerDTO);

    CustomerListDTO getCustomerById(Long id);
    List<CustomerListDTO> getAllCustomers();
}
