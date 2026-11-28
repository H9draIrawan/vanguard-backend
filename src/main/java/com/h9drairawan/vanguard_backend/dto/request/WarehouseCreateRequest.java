package com.h9drairawan.vanguard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record WarehouseCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String code,
        @NotBlank
        String address
) {
}
