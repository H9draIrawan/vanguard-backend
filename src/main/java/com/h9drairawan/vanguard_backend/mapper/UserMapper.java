package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.UserRequest;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;
import com.h9drairawan.vanguard_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User toEntity(UserRequest dto) {
        User entity = new User();
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        return entity;
    }

    public UserResponse toResponse(User entity) {
        return new UserResponse(
                entity.getName(),
                entity.getEmail(),
                entity.getRole() != null
                        ? roleMapper.toResponse(entity.getRole())
                        : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
