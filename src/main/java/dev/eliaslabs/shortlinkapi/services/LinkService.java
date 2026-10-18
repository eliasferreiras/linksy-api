package dev.eliaslabs.shortlinkapi.services;

import dev.eliaslabs.shortlinkapi.dto.request.LinkRequestDTO;
import dev.eliaslabs.shortlinkapi.dto.response.LinkResponseDTO;
import dev.eliaslabs.shortlinkapi.entities.Link;
import dev.eliaslabs.shortlinkapi.generators.ShortCodeGenerator;
import dev.eliaslabs.shortlinkapi.repositories.LinkRepository;
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
