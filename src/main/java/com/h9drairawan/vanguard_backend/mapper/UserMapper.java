package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.UserCreateRequest;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;
import com.h9drairawan.vanguard_backend.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User toEntity(UserCreateRequest dto) {
        User entity = new User();
        LocalDateTime now = LocalDateTime.now();

        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        return entity;
    }

    public UserResponse toResponse(User entity) {
        return new UserResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getStatus(),
                roleMapper.toResponse(entity.getRole()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
