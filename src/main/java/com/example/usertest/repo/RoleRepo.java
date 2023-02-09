package com.example.usertest.repo;

import com.example.usertest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByRoleName(String admin);
    @Query(value = "SELECT r FROM Role r WHERE r.roleName =:roleName ")
    Role findByrName(String roleName);
}
