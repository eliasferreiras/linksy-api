package dev.eliaslabs.linksy.controllers;

import dev.eliaslabs.linksy.dto.request.LinkRequestDTO;
import dev.eliaslabs.linksy.dto.response.LinkResponseDTO;
import dev.eliaslabs.linksy.services.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
@Validated
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public List<LinkResponseDTO> findAll() {
        return linkService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LinkResponseDTO save(@Valid @RequestBody LinkRequestDTO linkRequestDTO) {
        return linkService.save(linkRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        linkService.deleteById(id);
    }

}
