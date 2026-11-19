package com.h9drairawan.vanguard_backend.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        String name,
        String email,
        RoleResponse role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
