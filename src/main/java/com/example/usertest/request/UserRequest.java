package com.example.usertest.request;

import com.example.usertest.entity.Account;
import com.example.usertest.entity.Addresses;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String userName;
    private String password;
    private String mobileNumber;
    private List<Addresses> addresses;
    private Account account;
}
