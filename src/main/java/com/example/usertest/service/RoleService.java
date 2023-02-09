package com.example.usertest.service;

import com.example.usertest.dto.RoleDto;
import com.example.usertest.entity.Role;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    RoleDto getRoleById(int id);

    RoleDto updateRole(int id, RoleDto roleDto);

    void deleteUser(int id);

    Role findByRoleName(String roleName);

     Role findByrName(String roleName);
}
