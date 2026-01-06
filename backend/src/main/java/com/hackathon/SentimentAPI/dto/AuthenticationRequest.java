package com.hackathon.SentimentAPI.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "Username não pode ser vazio")
        String username,
        @NotBlank(message = "Password não pode ser vazio")
        String password) {
}
