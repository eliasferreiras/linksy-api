package dev.eliaslabs.linksy.configurations;

import dev.eliaslabs.linksy.entities.Role;
import dev.eliaslabs.linksy.entities.User;
import dev.eliaslabs.linksy.enums.RoleEnum;
import dev.eliaslabs.linksy.repositories.RoleRepository;
import dev.eliaslabs.linksy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.name}")
    private String adminName;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(String @NonNull ... args) {
        if (adminEmail.isBlank() || adminPassword.isBlank()) {
            return;
        }

        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN.name())
                .orElseThrow(() -> new IllegalStateException("ROLE_ADMIN is missing from the database"));

        if (userRepository.existsByRolesContaining(adminRole) ||
            userRepository.findByEmail(adminEmail).isPresent()) {
            return;
        }

        User admin = new User();
        admin.setName(adminName);
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRoles(Set.of(adminRole));

        userRepository.save(admin);
    }
}
