package com.h9drairawan.vanguard_backend.dto.response;

import java.time.LocalDateTime;

public record ItemResponse(
        Long id,
        String name,
        String sku,
        String description,
        Double price,
        Integer stock,
        CategoryResponse category,
        UserResponse createdBy,
        UserResponse updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
