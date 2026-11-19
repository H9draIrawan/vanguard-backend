package com.h9drairawan.vanguard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoleUpdateRequest(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
