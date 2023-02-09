package com.example.usertest.request;

import com.example.usertest.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String userName;
    private String password;
    private String mobileNumber;
}
