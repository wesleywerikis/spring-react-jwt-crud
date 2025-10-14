package com.devapp.jwtcrud.backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devapp.jwtcrud.backend.domain.Role;
import com.devapp.jwtcrud.backend.domain.User;
import com.devapp.jwtcrud.backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;
    private final PasswordEncoder enc;

    public UserService(UserRepository repo, PasswordEncoder enc) {
        this.repo = repo;
        this.enc = enc;
    }

    public User register(String username, String rawPwd) {
        if (repo.existsByUsername(username))
            throw new IllegalArgumentException("Username already exists");
        User u = User.builder().username(username).password(enc.encode(rawPwd)).role(Role.USER).build();
        return repo.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return org.springframework.security.core.userdetails.User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRole().name())
                .build();
    }
}
