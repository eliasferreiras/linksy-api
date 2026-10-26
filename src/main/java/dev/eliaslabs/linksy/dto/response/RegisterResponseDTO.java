package dev.eliaslabs.linksy.dto.response;

import dev.eliaslabs.linksy.entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterResponseDTO(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt
) {
    public RegisterResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
    }
}
