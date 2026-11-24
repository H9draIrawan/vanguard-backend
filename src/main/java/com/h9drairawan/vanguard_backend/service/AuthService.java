package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.LoginRequest;
import com.h9drairawan.vanguard_backend.dto.request.RegisterRequest;
import com.h9drairawan.vanguard_backend.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest dto);

    AuthResponse login(LoginRequest dto);
}
