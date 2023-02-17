package com.example.usertest.reponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String userName;
    private String mobileNumber;
    private List<RoleResponse> roles;
    private List<AddressResponse> addresses;
    private AccountResponse account;
}
