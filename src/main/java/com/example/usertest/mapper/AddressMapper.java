package com.example.usertest.mapper;

import com.example.usertest.dto.AddressDto;
import com.example.usertest.entity.Addresses;
import com.example.usertest.reponse.AddressResponse;
import com.example.usertest.request.AddressRequest;

import java.util.List;

public interface AddressMapper {
    AddressDto toAddressDto(AddressRequest addressRequest);

    Addresses toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Addresses saved);

    AddressResponse toAddressResponse(AddressDto savedAddressDto);

    Object toAddressResponse(List<AddressDto> addressDto);

}
