package dev.eliaslabs.linksy.dto.response;

public record LoginResponseDTO(
        String name,
        String username,
        String token
) {
}
