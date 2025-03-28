package com.juan.customerservice.controller;

import com.juan.customerservice.dto.AddressDTO;
import com.juan.customerservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/{customerId}/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<?> addAddress(@PathVariable("customerId") Long customerId,
                                        @RequestBody AddressDTO address) {
        addressService.createAddress(customerId, address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAddresses(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(addressService.getAllAddresses(customerId), HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddress(@PathVariable("customerId") Long customerId,
                                        @PathVariable("addressId") Long addressId) {
        return new ResponseEntity<>(addressService.getAddress(customerId, addressId), HttpStatus.OK);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable("customerId") Long customerId,
                                           @PathVariable("addressId") Long addressId, @RequestBody AddressDTO address) {
        addressService.updateAddress(customerId, addressId, address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("customerId") Long customerId,
                                           @PathVariable("addressId") Long addressId) {
        addressService.deleteAddress(customerId, addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
