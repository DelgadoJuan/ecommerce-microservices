package com.juan.customerservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
}
