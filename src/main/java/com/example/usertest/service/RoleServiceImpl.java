package com.example.usertest.service;

import com.example.usertest.dto.RoleDto;
import com.example.usertest.entity.Role;
import com.example.usertest.mapper.RoleMapper;
import com.example.usertest.repo.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    RoleRepo roleRepo;
    RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepo roleRepo,RoleMapper roleMapper){
        this.roleRepo=roleRepo;
        this.roleMapper=roleMapper;
    }
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role=roleMapper.toRoleEntity(roleDto);
        Role savedRole = roleRepo.save(role);
        RoleDto rDto = roleMapper.toRoleDto(savedRole);
        return rDto;
    }

    @Override
    public RoleDto getRoleById(int id) {
        Role role=roleRepo.getById(id);
        RoleDto roleDto = roleMapper.toRoleDto(role);

        return roleDto;
    }

    @Override
    public RoleDto updateRole(int id, RoleDto roleDto) {
        Role role=roleRepo.getById(id);
        role.setRoleName(roleDto.getRoleName());
        RoleDto savedRole=roleMapper.toRoleDto(role);
        return savedRole;
    }

    @Override
    public void deleteUser(int id) {
        Role role=roleRepo.getById(id);
        roleRepo.delete(role);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    @Override
    public Role findByrName(String roleName) {
        Role role = roleRepo.findByrName(roleName);
        return role;
    }
}
