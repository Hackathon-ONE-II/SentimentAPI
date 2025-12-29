package com.hackathon.SentimentAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SentimentResponse(
        @NotBlank
        @Size(min = 10)
        String text) {
}
