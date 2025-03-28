package com.juan.customerservice.service.Impl;

import com.juan.customerservice.exception.CustomerNotFoundException;
import com.juan.customerservice.mapper.CustomerMapper;
import com.juan.customerservice.dto.CustomerDTO;
import com.juan.customerservice.dto.CustomerListDTO;
import com.juan.customerservice.entity.CustomerEntity;
import com.juan.customerservice.repository.CustomerRepository;
import com.juan.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void deleteById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot delete customer:: No customer found with the provided ID: %s", id)
                ));
        customerRepository.delete(customerEntity);
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        try {
            CustomerEntity customerEntity = customerMapper.toEntity(customerDTO);
            customerRepository.save(customerEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error creating customer");
        }
    }

    @Override
    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with ID: %s", id)
        ));
        customerMapper.updateEntityFromDto(customerDTO, customerEntity);
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerListDTO getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with ID: %s", id)
                ));
        return customerMapper.toDto(customerEntity);
    }

    @Override
    public List<CustomerListDTO> getAllCustomers() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }
}
