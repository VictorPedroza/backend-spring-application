package com.worldcup.backend.modules.auth.mappers;

import com.worldcup.backend.modules.auth.dtos.AuthRequest;
import com.worldcup.backend.modules.auth.dtos.AuthResponse;
import com.worldcup.backend.modules.auth.dtos.RegisterRequest;
import com.worldcup.backend.shared.entities.User;

public class AuthMapper {
    public static User toEntity(RegisterRequest request) {
        User user = new User();

        user.setUsername(request.username());
        user.setPassword(request.password());

        return user;
    }

    public static User toEntity(AuthRequest request) {
        User user = new User();

        user.setUsername(request.username());
        user.setPassword(request.password());

        return user;
    }

    public static AuthResponse toDTO(String token) {
        return new AuthResponse(token);
    }
}
