package com.example.usertest.service;


import com.example.usertest.dto.UserDto;
import com.example.usertest.entity.Account;
import com.example.usertest.entity.Addresses;
import com.example.usertest.entity.Role;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.mapper.UserMapper;
import com.example.usertest.repo.AccountRepository;
import com.example.usertest.repo.RoleRepo;
import com.example.usertest.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepo roleRepo;

    private  final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private  final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity user = userMapper.toUserEntity(userDto);
        Role role= roleRepo.findByRoleName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        user.setMobileNumber(userDto.getMobileNumber());

        List<Addresses> addresses = userDto.getAddresses();
        Set<Addresses> addSet = new HashSet<>();
        for (Addresses adds:addresses){
            adds.setUser(user);
            addSet.add(adds);
        }
        //
        user.setAddresses(addSet);

        Account account = accountRepository.getById(userDto.getId());
        user.setAccount(account);

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
        List<Addresses> addresses = userDto.getAddresses();
        Set<Addresses> addSet = new HashSet<>();
        for (Addresses adds:addresses){
            adds.setUser(user);
            addSet.add(adds);
        }
        //
        user.setAddresses(addSet);
        Account account = accountRepository.getById(userDto.getId());
        user.setAccount(account);
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
        Role role= roleRepo.findByRoleName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(role));

        user.setMobileNumber(userDto.getMobileNumber());
        List<Addresses> addresses = userDto.getAddresses();
        Set<Addresses> addSet = new HashSet<>();
        for (Addresses adds:addresses){
            adds.setUser(user);
            addSet.add(adds);
        }

        user.setAddresses(addSet);
        Account account = accountRepository.getById(userDto.getId());

        user.setAccount(account);
        UserEntity savedUser = userRepository.save(user);
        UserDto udto = userMapper.toUserDto(savedUser);
        return udto;
    }

    @Override
    public UserEntity getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public UserDto updateAdmin(Integer id, UserDto userDto) {
        UserEntity user= userRepository.findById(id).get();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setMobileNumber(userDto.getMobileNumber());
        List<Addresses> addresses = userDto.getAddresses();
        Set<Addresses> addSet = new HashSet<>();
        for (Addresses adds:addresses){
            adds.setUser(user);
            addSet.add(adds);
        }
        user.setAddresses(addSet);

        Account account=user.getAccount();
        account.getAmount();
        UserEntity saved = userRepository.save(user);
        UserDto uDto = userMapper.toUserDto(saved);
        return uDto;
    }

}
