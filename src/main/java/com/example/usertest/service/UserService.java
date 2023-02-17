package com.example.usertest.service;


import com.example.usertest.dto.UserDto;
import com.example.usertest.entity.UserEntity;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(Integer id, UserDto userDto);

    UserDto getUserByUserId(Integer id);

    void deleteUser(Integer id);

    UserDto createAdmin(UserDto userDto);

    UserEntity getUserById(int id);

    UserDto updateAdmin(Integer id, UserDto userDto);

}
