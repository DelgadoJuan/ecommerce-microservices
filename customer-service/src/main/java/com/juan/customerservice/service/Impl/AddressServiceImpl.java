package com.juan.customerservice.service.Impl;

import com.juan.customerservice.dto.AddressDTO;
import com.juan.customerservice.entity.AddressEntity;
import com.juan.customerservice.entity.CustomerEntity;
import com.juan.customerservice.exception.AddressNotBelongingToCustomerException;
import com.juan.customerservice.exception.AddressNotFoundException;
import com.juan.customerservice.exception.CustomerNotFoundException;
import com.juan.customerservice.mapper.AddressMapper;
import com.juan.customerservice.repository.AddressRepository;
import com.juan.customerservice.repository.CustomerRepository;
import com.juan.customerservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final AddressMapper addressMapper;

    private CustomerEntity getCustomerOrThrow(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with ID: %s", customerId)));
    }

    private AddressEntity getAddressOrThrow(Long customerId, Long addressId) {
        AddressEntity addressEntity = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(
                        String.format("No address found with ID: %s", addressId)));

        if (!addressEntity.getCustomer().getId().equals(customerId)) {
            throw new AddressNotBelongingToCustomerException(
                    String.format("Address with ID %s does not belong to customer with ID %s", addressId, customerId));
        }
        return addressEntity;
    }

    @Override
    public void createAddress(Long customerId, AddressDTO addressDTO) {
        CustomerEntity customerEntity = getCustomerOrThrow(customerId);
        customerEntity.addAddress(addressMapper.toEntity(addressDTO));
        customerRepository.save(customerEntity);
    }

    @Override
    public void updateAddress(Long customerId, Long addressId, AddressDTO addressDTO) {
        getCustomerOrThrow(customerId);
        AddressEntity addressEntity = getAddressOrThrow(customerId, addressId);
        addressMapper.updateEntityFromDto(addressDTO, addressEntity);
        addressRepository.save(addressEntity);
    }

    @Override
    public void deleteAddress(Long customerId, Long addressId) {
        getCustomerOrThrow(customerId);
        AddressEntity addressEntity = getAddressOrThrow(customerId, addressId);
        addressRepository.delete(addressEntity);
    }

    @Override
    public AddressDTO getAddress(Long customerId, Long addressId) {
        getCustomerOrThrow(customerId);
        AddressEntity addressEntity = getAddressOrThrow(customerId, addressId);
        return addressMapper.toDto(addressEntity);
    }

    @Override
    public List<AddressDTO> getAllAddresses(Long customerId) {
        getCustomerOrThrow(customerId);
        return addressMapper.toDtoList(addressRepository.findAllByCustomerId(customerId));
    }
}
