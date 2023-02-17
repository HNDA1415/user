package com.example.usertest.mapper;

import com.example.usertest.dto.UserDto;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.reponse.UserResponse;
import com.example.usertest.request.UserRequest;

public interface UserMapper {
    UserDto toUserDto(UserRequest userRequest);

    UserEntity toUserEntity(UserDto userDto);

    UserDto toUserDto(UserEntity savedUser);

    UserResponse toUserResponse(UserDto savedUserDto);

    UserResponse toUserResponse(UserEntity user);

}




