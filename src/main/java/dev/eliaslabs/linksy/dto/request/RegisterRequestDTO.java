package dev.eliaslabs.linksy.dto.request;

import org.jspecify.annotations.NonNull;

public record RegisterRequestDTO(
        @NonNull String name,
        @NonNull String email,
        @NonNull String password
) {
}
