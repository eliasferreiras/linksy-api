package dev.eliaslabs.linksy.services;

import dev.eliaslabs.linksy.dto.request.LoginRequestDTO;
import dev.eliaslabs.linksy.dto.request.RegisterRequestDTO;
import dev.eliaslabs.linksy.dto.response.LoginResponseDTO;
import dev.eliaslabs.linksy.dto.response.RegisterResponseDTO;
import dev.eliaslabs.linksy.entities.Role;
import dev.eliaslabs.linksy.entities.User;
import dev.eliaslabs.linksy.enums.RoleEnum;
import dev.eliaslabs.linksy.exceptions.BadRequestException;
import dev.eliaslabs.linksy.providers.TokenProvider;
import dev.eliaslabs.linksy.repositories.RoleRepository;
import dev.eliaslabs.linksy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.email(),
                            loginRequestDTO.password()
                    )
            );

            User user = (User) authentication.getPrincipal();

            String token = tokenProvider.generateToken(authentication);

            return new LoginResponseDTO(user.getName(), user.getEmail(), token);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Invalid Credentials");
        } catch (Exception e) {
            throw new Exception("Unexpected error: " + e.getMessage());
        }
    }

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) throws BadRequestException {
        if (userRepository.findByEmail(registerRequestDTO.email()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        Role role = roleRepository.findByName(RoleEnum.ROLE_USER.name());
        String encodedPasswd = passwordEncoder.encode(registerRequestDTO.password());

        User user = new User();
        user.setName(registerRequestDTO.name());
        user.setEmail(registerRequestDTO.email());
        user.setPassword(encodedPasswd);
        user.setRoles(Set.of(role));

        return new RegisterResponseDTO(userRepository.save(user));
    }
}
