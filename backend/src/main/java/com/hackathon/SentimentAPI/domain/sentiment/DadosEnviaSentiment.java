package com.hackathon.SentimentAPI.domain.sentiment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosEnviaSentiment(
        @NotBlank
        @Size(min = 10)
        String text) {
}
