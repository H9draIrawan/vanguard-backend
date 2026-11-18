package com.h9drairawan.vanguard_backend.dto.request;

import com.h9drairawan.vanguard_backend.entity.Role;
import jakarta.validation.constraints.Email;

public record UserUpdateRequestDto(
        String name,

        @Email
        String email,

        String password,

        Role role
) {
}
