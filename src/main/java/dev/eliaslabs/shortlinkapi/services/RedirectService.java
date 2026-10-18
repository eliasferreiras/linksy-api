package dev.eliaslabs.shortlinkapi.services;

import dev.eliaslabs.shortlinkapi.entities.Link;
import dev.eliaslabs.shortlinkapi.repositories.LinkRepository;
import org.springframework.web.server.ResponseStatusException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedirectService {
    private LinkRepository linkRepository;

    public Link redirect(String shortCode) {
        return linkRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Link not found")
                );
    }
}
