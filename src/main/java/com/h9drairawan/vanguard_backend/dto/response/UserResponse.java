package com.h9drairawan.vanguard_backend.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String email,
        Boolean status,
        RoleResponse role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
