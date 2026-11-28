package com.h9drairawan.vanguard_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record WarehouseUpdateRequest(
        String name,
        String code,
        String address
) {
}
