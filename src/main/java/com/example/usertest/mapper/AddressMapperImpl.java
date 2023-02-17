package com.example.usertest.mapper;

import com.example.usertest.dto.AddressDto;
import com.example.usertest.entity.Addresses;
import com.example.usertest.reponse.AddressResponse;
import com.example.usertest.request.AddressRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapperImpl implements AddressMapper{
    @Override
    public AddressDto toAddressDto(AddressRequest addressRequest) {
        AddressDto addressDto=new AddressDto();
        addressDto.setCountry(addressRequest.getCountry());
        addressDto.setCity(addressRequest.getCity());
        addressDto.setStreetName(addressRequest.getStreetName());
        addressDto.setZipCode(addressRequest.getZipCode());

        return addressDto;

    }

    @Override
    public Addresses toAddressEntity(AddressDto addressDto) {
        Addresses addresses = new Addresses();
        addresses.setCountry(addressDto.getCountry());
        addresses.setCity(addressDto.getCity());
        addresses.setZipCode(addressDto.getZipCode());
        addresses.setStreetName(addressDto.getStreetName());
        return addresses;
    }
    @Override
    public AddressDto toAddressDto(Addresses saved) {
        if (saved == null){
            return null;
        }
        AddressDto addressDto=new AddressDto();
        addressDto.setId(saved.getId());
        addressDto.setCountry(saved.getCountry());
        addressDto.setCity(saved.getCity());
        addressDto.setZipCode(saved.getZipCode());
        addressDto.setStreetName(saved.getStreetName());
        return addressDto;
    }

    @Override
    public AddressResponse toAddressResponse(AddressDto savedAddressDto) {
        AddressResponse response = new AddressResponse();
        response.setId(savedAddressDto.getId());
        response.setCountry(savedAddressDto.getCountry());
        response.setCity(savedAddressDto.getCity());
        response.setZipCode(savedAddressDto.getZipCode());
        response.setStreetName(savedAddressDto.getStreetName());

        return response;
    }

    @Override
    public Object toAddressResponse(List<AddressDto> addressDto) {
        return null;
    }


}
