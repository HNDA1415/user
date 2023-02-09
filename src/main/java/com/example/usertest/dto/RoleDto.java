package com.example.usertest.dto;

import com.example.usertest.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RoleDto implements Serializable {
    private Integer id;
    private String roleName;
    //private List<UserEntity> userEntities;
}
