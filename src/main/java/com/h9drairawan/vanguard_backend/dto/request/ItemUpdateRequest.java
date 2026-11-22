package com.h9drairawan.vanguard_backend.dto.request;

public record ItemUpdateRequest(
        String name,
        String sku,
        String description,
        Double price,
        Integer stock,
        Long userId,
        Long categoryId
) {
}
