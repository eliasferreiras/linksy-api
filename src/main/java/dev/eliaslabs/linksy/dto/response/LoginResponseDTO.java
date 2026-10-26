package dev.eliaslabs.linksy.dto.response;

import java.util.Set;

public record LoginResponseDTO(
        String name,
        String username,
        Set<String> roles,
        String token
) {
}
