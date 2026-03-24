package com.example.userservice.service;

import com.example.userservice.dto.*;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.userservice.config.JwtUtil;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("Phone number already registered");
        }

        UserEntity user = new UserEntity();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new UserResponse(user.getId(), user.getPhoneNumber());
    }
    @Override
    public LoginResponse login(LoginRequest request) {

        UserEntity user = userRepository
                .findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(user.getPhoneNumber());

        return new LoginResponse(token);
    }

}
