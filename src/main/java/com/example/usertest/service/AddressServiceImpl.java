package com.example.usertest.service;

import com.example.usertest.dto.AddressDto;
import com.example.usertest.entity.Addresses;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.mapper.AddressMapper;
import com.example.usertest.repo.AddressRepository;
import com.example.usertest.repo.UserRepository;
import com.example.usertest.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public AddressDto createAddress(AddressDto addressDto, UserEntity user) {
        Addresses addresses = addressMapper.toAddressEntity(addressDto);
        addresses.setCountry(addressDto.getCountry());
        addresses.setCity(addressDto.getCity());
        addresses.setZipCode(addressDto.getZipCode());
        addresses.setStreetName(addressDto.getStreetName());
        addresses.setUser(user);
        Addresses saved = addressRepository.save(addresses);
        AddressDto aDto = addressMapper.toAddressDto(saved);
        return aDto;
    }

    @Override
    public AddressDto getAddressById(int id) {
        Optional<Addresses> addresses = addressRepository.findById(id);
        Addresses add = addresses.get();
        AddressDto addressDto = addressMapper.toAddressDto(add);
        return addressDto;
    }


    @Override
    public AddressDto updateAddress(int id, AddressDto addressDto) {
        Addresses addresses = addressRepository.findById(id).get();
        addresses.setCountry(addressDto.getCountry());
        addresses.setCity(addressDto.getCity());
        addresses.setZipCode(addressDto.getZipCode());
        addresses.setStreetName(addressDto.getStreetName());
        Addresses saved = addressRepository.save(addresses);
        AddressDto aDto = addressMapper.toAddressDto(saved);
        return aDto;


    }

    @Override
    public void deleteAddress(int id) {

        Optional<Addresses> addresses = addressRepository.findById(id);
        Addresses add = addresses.get();
        addressRepository.delete(add);

    }

    @Override
    public List<AddressDto> getAddresses(int id) {
        List<AddressDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper=new ModelMapper();

        UserEntity userEntity= userRepository.findByUserId(id);

        if (userEntity==null) return returnValue;

        Iterable<Addresses> addresses = addressRepository.findAllByUser(userEntity);
        for (Addresses addressEntity:addresses){
            returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
        }

        return returnValue;
    }
    @Override
    public List<AddressDto> getAddresses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        List<AddressDto> addressDtoList = new ArrayList<>();
        UserEntity user = userRepository.getById(userPrincipal.getId());
        Set<Addresses> addressList = user.getAddresses();
        for (Addresses address : addressList) {
            AddressDto addressDto = addressMapper.toAddressDto(address);
            addressDtoList.add(addressDto);
        }
        return addressDtoList;
    }
}
