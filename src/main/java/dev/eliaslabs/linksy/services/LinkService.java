package dev.eliaslabs.linksy.services;

import dev.eliaslabs.linksy.dto.request.LinkRequestDTO;
import dev.eliaslabs.linksy.dto.response.LinkResponseDTO;
import dev.eliaslabs.linksy.entities.Link;
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

    public List<LinkResponseDTO> findAll() {
        return linkRepository.findAll()
                .stream()
                .map(LinkResponseDTO::new)
                .toList();
    }

    public LinkResponseDTO save(LinkRequestDTO linkDTO) {
        String shortCode;

        do { // Risk of long (small chance of infinite loop). Do count for retries.
            shortCode = ShortCodeGenerator.generate();
        } while (linkRepository.existsByShortCode(shortCode));

        Link link = new Link(linkDTO);
        link.setShortCode(shortCode);

        return new LinkResponseDTO(linkRepository.save(link));
    }

    public void deleteById(UUID id) {
        linkRepository.deleteById(id);
    }
}
