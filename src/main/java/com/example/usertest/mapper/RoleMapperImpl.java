package com.example.usertest.mapper;

import com.example.usertest.dto.RoleDto;
import com.example.usertest.entity.Role;
import com.example.usertest.reponse.RoleRes;
import com.example.usertest.request.RoleReq;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper{

    @Override
    public RoleDto toRoleDto(RoleReq roleRes) {
        RoleDto roleDto= new RoleDto();
        roleDto.setRoleName(roleRes.getRoleName());
        //roleDto.setUserEntities(roleRes.getUserEntities());
        return roleDto;
    }

    @Override
    public Role toRoleEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        //role.setUserEntities(roleDto.getUserEntities());
        return role;
    }

    @Override
    public RoleDto toRoleDto(Role savedRole) {
        if (savedRole==null){
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(savedRole.getId());
        roleDto.setRoleName(savedRole.getRoleName());
        //roleDto.setUserEntities(savedRole.getUserEntities());
        return roleDto;
    }

    @Override
    public RoleRes toRoleRes(RoleDto savedRole) {
        RoleRes roleRes = new RoleRes();
        roleRes.setId(savedRole.getId());
        roleRes.setRoleName(savedRole.getRoleName());
        //roleRes.setUserEntities(savedRole.getUserEntities());
        return roleRes;
    }
}
