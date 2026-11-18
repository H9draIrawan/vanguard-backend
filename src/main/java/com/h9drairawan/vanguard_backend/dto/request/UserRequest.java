package com.h9drairawan.vanguard_backend.dto.request;

import com.h9drairawan.vanguard_backend.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        Role role
) {
}
