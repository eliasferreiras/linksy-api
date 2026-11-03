package dev.eliaslabs.linksy.controllers;

import dev.eliaslabs.linksy.dto.request.LinkRequestDTO;
import dev.eliaslabs.linksy.dto.response.LinkResponseDTO;
import dev.eliaslabs.linksy.entities.User;
import dev.eliaslabs.linksy.services.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<LinkResponseDTO> findAll(@AuthenticationPrincipal User user) {
        return linkService.findAllByUserId(user.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LinkResponseDTO save(@Valid @RequestBody LinkRequestDTO linkRequestDTO,
                                @AuthenticationPrincipal User user) {
        return linkService.save(linkRequestDTO, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id,
                       @AuthenticationPrincipal User user) {
        linkService.delete(id, user.getId());
    }

}
