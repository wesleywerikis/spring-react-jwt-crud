package com.devapp.jwtcrud.backend.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devapp.jwtcrud.backend.dto.AuthRequest;
import com.devapp.jwtcrud.backend.dto.AuthResponse;
import com.devapp.jwtcrud.backend.dto.RegisterRequest;
import com.devapp.jwtcrud.backend.security.JwtService;
import com.devapp.jwtcrud.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager am;
    private final JwtService jwt;
    private final UserService users;

    public AuthController(AuthenticationManager am, JwtService jwt, UserService users) {
        this.am = am;
        this.jwt = jwt;
        this.users = users;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest req) {
        am.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        var ud = users.loadUserByUsername(req.username());
        String role = ud.getAuthorities().stream().findFirst().get().getAuthority().replace("ROLE_", "");
        String token = jwt.generate(req.username(), role);
        return ResponseEntity.ok(new AuthResponse(token, req.username(), role));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest req) {
        var u = users.register(req.username(), req.password());
        String token = jwt.generate(u.getUsername(), u.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token, u.getUsername(), u.getRole().name()));
    }
}
