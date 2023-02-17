package com.example.usertest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    private String mobileNumber;
    private String password;
}
