package com.hackathon.SentimentAPI.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
