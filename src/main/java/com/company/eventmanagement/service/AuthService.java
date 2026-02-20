package com.company.eventmanagement.service;


import com.company.eventmanagement.dto.AuthResponse;
import com.company.eventmanagement.dto.LoginRequest;
import com.company.eventmanagement.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}