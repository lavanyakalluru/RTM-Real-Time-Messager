package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {

    private String phoneNumber;
    private String password;

}
