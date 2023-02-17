package com.example.usertest.service;

import com.example.usertest.dto.AddressDto;
import com.example.usertest.entity.UserEntity;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto, UserEntity user);

    AddressDto getAddressById(int id);

    AddressDto updateAddress(int id, AddressDto addressDto);

    void deleteAddress(int id);

    List<AddressDto> getAddresses(int id);

    List<AddressDto> getAddresses();
}
