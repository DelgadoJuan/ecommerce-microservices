package com.juan.customerservice.mapper;

import com.juan.customerservice.dto.CustomerDTO;
import com.juan.customerservice.dto.CustomerListDTO;
import com.juan.customerservice.entity.CustomerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerListDTO toDto(CustomerEntity entity);
    CustomerEntity toEntity(CustomerDTO customerDTO);
    List<CustomerListDTO> toDtoList(List<CustomerEntity> entity);
    void updateEntityFromDto(CustomerDTO dto, @MappingTarget CustomerEntity entity);
}
