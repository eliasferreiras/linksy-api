package dev.eliaslabs.linksy.dto.request;

import org.jspecify.annotations.NonNull;

public record LoginRequestDTO(
        @NonNull String email,
        @NonNull String password
) {
}
