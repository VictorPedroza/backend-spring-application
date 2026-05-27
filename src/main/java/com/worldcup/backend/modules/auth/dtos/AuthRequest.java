package com.worldcup.backend.modules.auth.dtos;

public record AuthRequest(
    String username,
    String password
) {}
