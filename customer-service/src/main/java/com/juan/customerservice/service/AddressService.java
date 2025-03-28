package com.juan.customerservice.service;

import com.juan.customerservice.dto.AddressDTO;
import java.util.List;

public interface AddressService {
    void createAddress(Long customerId, AddressDTO addressDTO);
    void updateAddress(Long customerId, Long addressId, AddressDTO addressDTO);
    void deleteAddress(Long customerId, Long addressId);

    AddressDTO getAddress(Long customerId, Long addressId);
    List<AddressDTO> getAllAddresses(Long customerId);
}
