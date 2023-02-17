package com.example.usertest.mapper;

import com.example.usertest.dto.UserDto;
import com.example.usertest.entity.Addresses;
import com.example.usertest.entity.Role;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.reponse.AccountResponse;
import com.example.usertest.reponse.AddressResponse;
import com.example.usertest.reponse.RoleResponse;
import com.example.usertest.reponse.UserResponse;
import com.example.usertest.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserMapperImpl implements  UserMapper{


    @Override
    public UserDto toUserDto(UserRequest userRequest) {
        UserDto userDto=new UserDto();
        userDto.setUserName(userRequest.getUserName());
        userDto.setPassword(userRequest.getPassword());
        userDto.setMobileNumber(userRequest.getMobileNumber());
        userDto.setAddresses(userRequest.getAddresses());
        //userDto.setAccount(userRequest.getAccount());
        return userDto;
    }

    @Override
    public UserEntity toUserEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setMobileNumber(userDto.getMobileNumber());

        return user;
    }

    @Override
    public UserDto toUserDto(UserEntity user) {
        if(user==null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setMobileNumber(user.getMobileNumber());
        userDto.setRoles(new ArrayList<>(user.getRoles()));
        userDto.setAddresses(new ArrayList<>(user.getAddresses()));
        userDto.setAccount(user.getAccount());
        return userDto;
    }

    @Override
    public UserResponse toUserResponse(UserDto user) {
        UserResponse response= new UserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setMobileNumber(user.getMobileNumber());

        List<RoleResponse> responses = new ArrayList<>();
        List<Role>roles = user.getRoles();
        for (Role role : roles){
            RoleResponse rResponse = new RoleResponse();
            rResponse.setId(role.getId());
            rResponse.setRoleName(role.getRoleName());
            responses.add(rResponse);
        }
        response.setRoles(responses);

        List<AddressResponse> addressResponses = new ArrayList<>();
        List<Addresses> addresses = user.getAddresses();
        if (addresses == null){
            response.setAddresses(null);
        }else {
            for (Addresses adds:addresses){
                AddressResponse addressResponse = new AddressResponse();
                addressResponse.setId(adds.getId());
                addressResponse.setCountry(adds.getCountry());
                addressResponse.setCity(adds.getCity());
                addressResponse.setZipCode(adds.getZipCode());
                addressResponse.setStreetName(adds.getStreetName());
                addressResponses.add(addressResponse);
            }
            response.setAddresses(addressResponses);
        }

        AccountResponse accountResponse = new AccountResponse();
        if (user.getAccount() == null){
            response.setAccount(null);
        }else {
            accountResponse.setId(user.getAccount().getId());
            accountResponse.setAmount(user.getAccount().getAmount());
            response.setAccount(accountResponse);
        }


        return response;
    }

    @Override
    public UserResponse toUserResponse(UserEntity user) {
        if (user==null)
        {
            return null;
        }
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setMobileNumber(user.getMobileNumber());

        Set<RoleResponse> responses = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles){
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setId(role.getId());
            roleResponse.setRoleName(role.getRoleName());
            responses.add(roleResponse);
        }
        response.setRoles(new ArrayList<>(responses));

        Set<AddressResponse> addressResponses = new HashSet<>();
        Set<Addresses> addresses = user.getAddresses();
        if (addresses == null){
            response.setAddresses(null);
        }else {
            for (Addresses addses : addresses){
                AddressResponse aResponse = new AddressResponse();
                aResponse.setId(addses.getId());
                aResponse.setCountry(addses.getCountry());
                aResponse.setCity(addses.getCity());
                aResponse.setZipCode(addses.getZipCode());
                aResponse.setStreetName(addses.getStreetName());
                addressResponses.add(aResponse);
            }
            response.setAddresses(new ArrayList<>(addressResponses));
        }

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(user.getAccount().getId());
        accountResponse.setAmount(user.getAccount().getAmount());
        response.setAccount(accountResponse);

        return response;

    }


}
