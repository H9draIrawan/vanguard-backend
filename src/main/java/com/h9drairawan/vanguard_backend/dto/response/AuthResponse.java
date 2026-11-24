package com.h9drairawan.vanguard_backend.dto.response;

public record AuthResponse(
        String token,
        String type,
        String username
) {
}
