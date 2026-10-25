package dev.eliaslabs.linksy.dto.response;

import dev.eliaslabs.linksy.entities.User;

import java.util.UUID;

public record RegisterResponseDTO(
        UUID id,
        String name,
        String email
) {
    public RegisterResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
