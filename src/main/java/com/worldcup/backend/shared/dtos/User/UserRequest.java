package com.worldcup.backend.shared.dtos.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "Usuário não pode ser vazio.")
    @Size(min = 3, max = 30, message = "O Usuário não poder ser menor que 3 caracteres e maior que 30.")
    String username,

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha não pode ser menor que 6 caracteres.")
    String password
) {}
