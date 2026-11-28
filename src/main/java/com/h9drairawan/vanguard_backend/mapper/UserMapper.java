package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.CreateUserDto;
import com.h9drairawan.vanguard_backend.dto.response.UserDto;
import com.h9drairawan.vanguard_backend.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public User toEntity(CreateUserDto dto) {
        User entity = new User();
        LocalDateTime now = LocalDateTime.now();

        entity.setUsername(dto.username());
        entity.setEmail(dto.email());
        entity.setRole(dto.role());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        return entity;
    }

    public UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRole()
        );
    }
}
