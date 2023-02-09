package com.example.usertest.controller;


import com.example.usertest.dto.RoleDto;
import com.example.usertest.dto.UserDto;
import com.example.usertest.entity.Role;
import com.example.usertest.entity.UserEntity;
import com.example.usertest.mapper.RoleMapper;
import com.example.usertest.mapper.UserMapper;
import com.example.usertest.repo.RoleRepo;
import com.example.usertest.reponse.RoleRes;
import com.example.usertest.reponse.UserResponse;
import com.example.usertest.request.UserRequest;
import com.example.usertest.service.RoleService;
import com.example.usertest.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;
    private RoleService roleService;
    private RoleMapper roleMapper;
    private RoleRepo roleRepo;

    public UserController(UserService userService, UserMapper userMapper, RoleService roleService, RoleMapper roleMapper,RoleRepo roleRepo) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.roleRepo=roleRepo;
    }

    @PostMapping(path = "/user")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {


        UserDto userDto = userMapper.toUserDto(userRequest);
        UserDto savedUserDto = userService.createUser(userDto);
        UserResponse response = userMapper.toUserResponse(savedUserDto);
        return response;

    }

    @PostMapping(path = "/admin")
    public UserResponse createAdmin(@RequestBody UserRequest userRequest) {

        UserDto userDto = userMapper.toUserDto(userRequest);
        UserDto savedUserDto = userService.createAdmin(userDto);
        UserResponse response = userMapper.toUserResponse(savedUserDto);
        return response;
    }

    @GetMapping(path = "/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getUserByUserId(id);
        UserResponse response = userMapper.toUserResponse(userDto);
        return response;
    }

    @PutMapping(path = "/user/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse UpdateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        UserDto userDto = userMapper.toUserDto(userRequest);
        UserDto saved = userService.updateUser(id, userDto);
        UserResponse response = userMapper.toUserResponse(saved);

        return response;
    }
    @PutMapping(path = "/admin/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserResponse UpdateAdmin(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        UserDto userDto = userMapper.toUserDto(userRequest);
        UserDto saved = userService.updateUser(id, userDto);
        UserResponse response = userMapper.toUserResponse(saved);

        return response;
    }
   /* @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }*/
    @DeleteMapping(path = "/{id}")
    public void DeleteUser1(@PathVariable int id){
        userService.deleteUser(id);
    }


    @GetMapping("/users/roles")
    public List<UserResponse> getUsers(@RequestParam(value = "r") String roleName){

        List<UserResponse> responses = new ArrayList<>();

        //Role role = roleService.findByRoleName(roleName);

        Role role1=roleRepo.findByrName(roleName);

        Set<UserEntity>users=role1.getUserEntities();
        for (UserEntity user : users){
            UserResponse response = userMapper.toUserResponse(user);
            responses.add(response);
        }
        return responses;

}


}
