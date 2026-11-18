package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
}
