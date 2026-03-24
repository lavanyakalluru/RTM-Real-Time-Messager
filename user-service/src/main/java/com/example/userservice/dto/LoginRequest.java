package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String phoneNumber;
    private String password;

}
