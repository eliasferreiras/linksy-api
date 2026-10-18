package dev.eliaslabs.shortlinkapi.controllers;

import dev.eliaslabs.shortlinkapi.dto.request.LinkRequestDTO;
import dev.eliaslabs.shortlinkapi.dto.response.LinkResponseDTO;
import dev.eliaslabs.shortlinkapi.entities.Link;
import dev.eliaslabs.shortlinkapi.services.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/links")
@AllArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public List<LinkResponseDTO> findAll() {
        return linkService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LinkResponseDTO save(@RequestBody LinkRequestDTO linkRequestDTO) {
        return linkService.save(linkRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id) {
        linkService.deleteById(id);
    }

}
