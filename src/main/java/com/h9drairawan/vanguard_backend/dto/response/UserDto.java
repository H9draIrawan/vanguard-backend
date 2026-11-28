package com.h9drairawan.vanguard_backend.dto.response;

public record UserDto(
        Long id,
        String username,
        String email,
        String role
) {
}
