package com.example.usertest.service;


import com.example.usertest.dto.UserDto;
import com.example.usertest.entity.Role;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.mapper.UserMapper;
import com.example.usertest.repo.RoleRepo;
import com.example.usertest.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepo roleRepo;

    private  final UserRepository userRepository;

    private  final UserMapper userMapper;
    /* public UserServiceImpl(UserRepository userRepository){
         this.userRepository = userRepository;
     }*/
    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity user = userMapper.toUserEntity(userDto);
        Role role= roleRepo.findByRoleName("USER");
        user.setRoles(Collections.singleton(role));
        user.setMobileNumber(userDto.getMobileNumber());
        UserEntity savedUser = userRepository.save(user);
        UserDto udto = userMapper.toUserDto(savedUser);
        return udto;
    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        UserEntity user= userRepository.findById(id).get();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setMobileNumber(userDto.getMobileNumber());
        UserEntity saved = userRepository.save(user);
        UserDto uDto = userMapper.toUserDto(saved);

        return uDto;
    }

    @Override
    public UserDto getUserByUserId(Integer id) {
        Optional<UserEntity> userEntity=userRepository.findById(id);
        UserEntity user = userEntity.get();
        UserDto userDto = userMapper.toUserDto(user);
        return userDto;
    }

    @Override
    public void deleteUser(Integer id) {
        Optional<UserEntity> userEntity=userRepository.findById(id);
        UserEntity user = userEntity.get();
        userRepository.delete(user);
    }

    @Override
    public UserDto createAdmin(UserDto userDto) {
        UserEntity user = userMapper.toUserEntity(userDto);
        Role role= roleRepo.findByRoleName("ADMIN");
        user.setRoles(Collections.singleton(role));
        user.setMobileNumber(userDto.getMobileNumber());
        UserEntity savedUser = userRepository.save(user);
        UserDto udto = userMapper.toUserDto(savedUser);
        return udto;
    }

    /*@Override
    public UserDto updateAdmin(Integer id, UserDto userDto) {

        Role admin= roleRepo.findByRoleName("ADMIN");
        admin.setUserName(userDto.getUserName());
        admin.setPassword(userDto.getPassword());
        admin.setMobileNumber(userDto.getMobileNumber());
        UserEntity saved = userRepository.save(admin);
        UserDto uDto = userMapper.toUserDto(saved);

        return uDto;
    }*/



/*    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity user=userMapper.toUserEntity(userDto);
        UserEntity saved = userRepository.save(user);
        UserDto uDto = userMapper.toUserDto(saved);
        return uDto;
    }*/

}
