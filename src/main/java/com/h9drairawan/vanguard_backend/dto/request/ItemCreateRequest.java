package com.h9drairawan.vanguard_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String sku,
        @NotBlank
        String description,
        @NotNull
        Double price,
        @NotNull
        Integer stock,
        @NotNull
        Long userId,
        @NotNull
        Long categoryId
) {
}
