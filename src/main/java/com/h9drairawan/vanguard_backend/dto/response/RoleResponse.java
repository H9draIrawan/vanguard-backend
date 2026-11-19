package com.h9drairawan.vanguard_backend.dto.response;

import java.time.LocalDateTime;

public record RoleResponse(
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
