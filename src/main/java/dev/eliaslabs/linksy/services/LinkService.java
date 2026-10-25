package dev.eliaslabs.linksy.services;

import dev.eliaslabs.linksy.dto.request.LinkRequestDTO;
import dev.eliaslabs.linksy.dto.response.LinkResponseDTO;
import dev.eliaslabs.linksy.entities.Link;
import dev.eliaslabs.linksy.entities.User;
import dev.eliaslabs.linksy.exceptions.NotFoundException;
import dev.eliaslabs.linksy.generators.ShortCodeGenerator;
import dev.eliaslabs.linksy.repositories.LinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;

    public List<LinkResponseDTO> findAllByUserId(UUID userId) {
        return linkRepository.findAllByUserId(userId)
                .stream()
                .map(LinkResponseDTO::new)
                .toList();
    }

    public LinkResponseDTO save(LinkRequestDTO linkDTO, User user) {
        String shortCode;

        do { // Risk of long (small chance of infinite loop). Do count for retries.
            shortCode = ShortCodeGenerator.generate();
        } while (linkRepository.existsByShortCode(shortCode));

        Link link = new Link(linkDTO);
        link.setShortCode(shortCode);
        link.setUser(user);

        return new LinkResponseDTO(linkRepository.save(link));
    }

    public void delete(UUID id, UUID userId) {
        Link link = linkRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException("Link not found"));

        linkRepository.delete(link);
    }
}
