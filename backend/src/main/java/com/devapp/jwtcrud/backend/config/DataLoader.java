package com.devapp.jwtcrud.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devapp.jwtcrud.backend.domain.Role;
import com.devapp.jwtcrud.backend.domain.User;
import com.devapp.jwtcrud.backend.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seed(UserRepository repo, PasswordEncoder enc) {
        return args -> {
            if (!repo.existsByUsername("admin")) {
                repo.save(User.builder()
                        .username("admin")
                        .password(enc.encode("admin123"))
                        .role(Role.ADMIN)
                        .build());
            }
        };
    }
}
