package com.juan.customerservice.mapper;

import com.juan.customerservice.dto.AddressDTO;
import com.juan.customerservice.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDto(AddressEntity entity);
    AddressEntity toEntity(AddressDTO dto);
    List<AddressDTO> toDtoList(List<AddressEntity> entity);
    void updateEntityFromDto(AddressDTO dto, @MappingTarget AddressEntity entity);
}
