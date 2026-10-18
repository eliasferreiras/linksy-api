package dev.eliaslabs.linksy.repositories;

import dev.eliaslabs.linksy.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
    boolean existsByShortCode(String shortCode);
    Optional<Link> findByShortCode(String shortCode);
}
