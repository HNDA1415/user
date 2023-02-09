package com.example.usertest.service;


import com.example.usertest.dto.UserDto;
import com.example.usertest.request.UserRequest;

public interface UserService {

    UserDto createUser(UserDto user);

   // UserDto getUser(UserDto userDto);

    UserDto updateUser(Integer id, UserDto userDto);

    UserDto getUserByUserId(Integer id);

    void deleteUser(Integer id);

    UserDto createAdmin(UserDto userDto);

   // UserDto updateAdmin(Integer id, UserDto userDto);
}
