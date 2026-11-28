package com.h9drairawan.vanguard_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDto(
        @Size(min = 8)
        String username,
        @Email
        String email,
        @Size(min = 8)
        String password,
        String role
) {
}
