package com.example.usertest.dto;

import com.example.usertest.entity.Account;
import com.example.usertest.entity.Addresses;
import com.example.usertest.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserDto implements Serializable {

    private Integer id;
    private String userName;
    private String password;
    private String mobileNumber;
    private List<Role> roles;
    private List<Addresses> addresses;
    private Account account;

}
