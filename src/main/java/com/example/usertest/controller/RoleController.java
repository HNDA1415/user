package com.example.usertest.controller;

import com.example.usertest.dto.RoleDto;
import com.example.usertest.mapper.RoleMapper;
import com.example.usertest.mapper.UserMapper;
import com.example.usertest.reponse.RoleRes;
import com.example.usertest.request.RoleReq;
import com.example.usertest.service.RoleService;
import com.example.usertest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("roles")
public class RoleController {

    private RoleService roleService;
    private RoleMapper roleMapper;
    private UserService userService;
    private UserMapper userMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper, UserService userService, UserMapper userMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public RoleRes createRole(@RequestBody RoleReq roleReq){

        RoleDto roleDto = roleMapper.toRoleDto(roleReq);
        RoleDto savedRole = roleService.createRole(roleDto);
        RoleRes roleRes1 = roleMapper.toRoleRes(savedRole);
        return roleRes1;

    }
    @GetMapping(path = "/{id}")
    public RoleRes getRoles(@PathVariable("id") int id){
        RoleDto roleDto=roleService.getRoleById(id);
        RoleRes roleRes1=roleMapper.toRoleRes(roleDto);
        return roleRes1;

    }
    @PutMapping(path = "/{id}")
    public RoleRes UpdateRole(@PathVariable("id") int id,@RequestBody RoleReq roleReq){
        RoleDto roleDto= roleMapper.toRoleDto(roleReq);
        RoleDto savedRole=roleService.updateRole(id,roleDto);
        RoleRes roleRes = roleMapper.toRoleRes(savedRole);
        return roleRes;

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> DeleteRole(@PathVariable("id") int id){
        roleService.deleteUser(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
