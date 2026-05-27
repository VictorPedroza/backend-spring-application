package com.worldcup.backend.modules.auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.worldcup.backend.modules.auth.dtos.AuthRequest;
import com.worldcup.backend.modules.auth.dtos.AuthResponse;
import com.worldcup.backend.modules.auth.dtos.RegisterRequest;
import com.worldcup.backend.modules.auth.mappers.AuthMapper;
import com.worldcup.backend.modules.auth.repositories.AuthRepository;

import com.worldcup.backend.shared.entities.User;

@Service
public class AuthService {

    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            AuthRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        if (repository.existsByUsername(request.username())) {
            throw new RuntimeException("Usuário já utilizado.");
        }
        User user = AuthMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return new AuthResponse(jwtService.generateToken(request.username()));
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()));

        return new AuthResponse(jwtService.generateToken(request.username()));
    }
}
