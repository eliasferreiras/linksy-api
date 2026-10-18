package dev.eliaslabs.shortlinkapi.services;

import dev.eliaslabs.shortlinkapi.dto.request.LinkRequestDTO;
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

    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    public Link save(LinkRequestDTO linkDTO) {
        String shortCode;

        do { // Risk of long (small chance of infinite loop). Do count for retries.
            shortCode = ShortCodeGenerator.generate();
        } while (linkRepository.existsByShortCode(shortCode));

        Link link = new Link();
        link.setShortCode(shortCode);
        link.setTargetURL(linkDTO.getTargetURL());
        link.setTitle(linkDTO.getTitle());

        return linkRepository.save(link);
    }

    public void deleteById(UUID id) {
        linkRepository.deleteById(id);
    }
}
