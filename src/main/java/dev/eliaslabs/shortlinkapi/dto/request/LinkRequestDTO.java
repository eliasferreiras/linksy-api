package dev.eliaslabs.shortlinkapi.dto.request;

public record LinkRequestDTO(
    String targetURL,
    String title
) {}
