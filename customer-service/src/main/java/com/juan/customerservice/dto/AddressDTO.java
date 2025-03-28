package com.juan.customerservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    @NotNull(message = "Address street is required")
    private String street;
    @NotNull(message = "Address city is required")
    private String city;
    @NotNull(message = "Address state is required")
    private String state;
    @NotNull(message = "Address zip is required")
    @Positive(message = "Zip code must be positive")
    private String zipCode;
    @NotNull(message = "Address country is required")
    private String country;
}
