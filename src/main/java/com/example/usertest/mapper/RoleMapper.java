package com.example.usertest.mapper;

import com.example.usertest.dto.RoleDto;
import com.example.usertest.entity.Role;
import com.example.usertest.reponse.RoleRes;
import com.example.usertest.request.RoleReq;

public interface RoleMapper {
    RoleDto toRoleDto(RoleReq roleRes);

    Role toRoleEntity(RoleDto roleDto);

    RoleDto toRoleDto(Role savedRole);

    RoleRes toRoleRes(RoleDto savedRole);
}
