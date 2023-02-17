package com.example.usertest.controller;

import com.example.usertest.dto.AddressDto;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.mapper.AddressMapper;
import com.example.usertest.reponse.AddressResponse;
import com.example.usertest.request.AddressRequest;
import com.example.usertest.security.UserPrincipal;
import com.example.usertest.service.AddressService;
import com.example.usertest.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class AddressController {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/addresses")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AddressResponse createAddress(@RequestBody AddressRequest addressRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        UserEntity user = userService.getUserById(userPrincipal.getId());
        AddressDto addressDto = addressMapper.toAddressDto(addressRequest);
        AddressDto savedAddressDto = addressService.createAddress(addressDto, user);
        AddressResponse response = addressMapper.toAddressResponse(savedAddressDto);

        return response;
    }

    @GetMapping(path = "address/{id}")
    public AddressResponse getAddresses(@PathVariable("id") int id) {
        AddressDto addressDto = addressService.getAddressById(id);
        AddressResponse response = addressMapper.toAddressResponse(addressDto);
        return response;
    }


    @GetMapping(path = "{id}/address")
    public List<AddressResponse> getCurrentAddresses(@PathVariable int id) {
        List<AddressResponse> returnValue = new ArrayList<>();

        List<AddressDto> addressesDto = addressService.getAddresses(id);
        if (addressesDto != null && !addressesDto.isEmpty()) {

            Type listType = new TypeToken<List<AddressResponse>>() {
            }.getType();

            returnValue = new ModelMapper().map(addressesDto, listType);
        }

        return returnValue;

    }

    @PutMapping(path = "address/{id}")
    public AddressResponse UpdateAddress(@PathVariable int id, @RequestBody AddressRequest addressRequest) {
        AddressDto addressDto = addressMapper.toAddressDto(addressRequest);
        AddressDto saved = addressService.updateAddress(id, addressDto);
        AddressResponse response = addressMapper.toAddressResponse(saved);
        return response;
    }


    @DeleteMapping(path = "address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(path = "/address/get")
    public List<AddressResponse> getUserAddresses() {
        List<AddressResponse> addresses = new ArrayList<>();
        List<AddressDto> addressDtos = addressService.getAddresses();
        for (AddressDto addressDto : addressDtos) {
            AddressResponse response = addressMapper.toAddressResponse(addressDto);
            addresses.add(response);
        }
        return addresses;
    }


}
