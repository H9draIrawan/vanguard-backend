package com.h9drairawan.vanguard_backend.dto.response;

import com.h9drairawan.vanguard_backend.entity.Role;

import java.time.LocalDateTime;

public record UserResponse(
        String name,
        String email,
        RoleResponse roleResponse,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
