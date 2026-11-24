package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.RegisterRequest;
import com.h9drairawan.vanguard_backend.dto.request.UserCreateRequest;
import com.h9drairawan.vanguard_backend.entity.User;

import java.time.LocalDateTime;

public class AuthMapper {
    public User toEntity(RegisterRequest dto) {
        User entity = new User();
        LocalDateTime now = LocalDateTime.now();

        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        return entity;
    }
}
