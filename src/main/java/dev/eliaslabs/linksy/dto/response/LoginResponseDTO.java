package dev.eliaslabs.linksy.dto.response;

public record LoginResponseDTO(
        String token,
        String username
) {
}
