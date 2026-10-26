package dev.eliaslabs.linksy.controllers;

import dev.eliaslabs.linksy.dto.request.LoginRequestDTO;
import dev.eliaslabs.linksy.dto.request.RegisterRequestDTO;
import dev.eliaslabs.linksy.dto.response.LoginResponseDTO;
import dev.eliaslabs.linksy.dto.response.RegisterResponseDTO;
import dev.eliaslabs.linksy.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws Exception {
        return authenticationService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        return authenticationService.register(registerRequestDTO);
    }
}
