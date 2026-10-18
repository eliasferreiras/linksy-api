package dev.eliaslabs.linksy.controllers;

import dev.eliaslabs.linksy.entities.Link;
import dev.eliaslabs.linksy.services.RedirectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class RedirectController {

    private final RedirectService redirectService;

    @GetMapping("/{short_code}")
    public ResponseEntity<Void> redirect(@PathVariable("short_code") String shortCode) {
        Link link = redirectService.redirect(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(java.net.URI.create(link.getTargetURL()))
                .build();
    }
}
