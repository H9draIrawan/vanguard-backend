package com.h9drairawan.vanguard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWarehouseDto(
        @NotBlank
        @Size(min = 8)
        String name,
        @NotBlank
        @Size(min = 8)
        String address
) {
}
