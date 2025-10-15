package com.devapp.jwtcrud.backend.dto;

public record AuthResponse(
        String token,
        String username,
        String role) {
}
