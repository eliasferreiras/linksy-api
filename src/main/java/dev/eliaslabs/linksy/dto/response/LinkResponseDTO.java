package dev.eliaslabs.linksy.dto.response;

import dev.eliaslabs.linksy.entities.Link;

import java.time.LocalDateTime;
import java.util.UUID;

public record LinkResponseDTO(
        UUID id,
        String shortCode,
        String targetURL,
        String title,
        LocalDateTime createdAt
) {
    public LinkResponseDTO(Link link) {
        this(link.getId(), link.getShortCode(), link.getTargetURL(), link.getTitle(), link.getCreatedAt());
    }
}
