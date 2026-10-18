package dev.eliaslabs.linksy.dto.request;

import jakarta.validation.constraints.NotNull;

public record LinkRequestDTO(
    @NotNull String targetURL,
    String title
) {}
