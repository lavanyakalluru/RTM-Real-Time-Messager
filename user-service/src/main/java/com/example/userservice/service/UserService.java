package com.example.userservice.service;

import com.example.userservice.dto.*;

public interface UserService {

    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}
