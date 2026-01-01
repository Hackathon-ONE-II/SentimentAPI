package com.hackathon.SentimentAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record SentimentRequest(
        // validação para o campo text min e max size
        @NotBlank(message = "{sentiment.text.notBlank}")
        @Size(min = 5, max = 1300, message = "{sentiment.text.size}")
        @Pattern(
                regexp = ".*[a-zA-Z].*",
                message = "O texto deve conter pelo menos uma palavra"
        )
        String text

) {
        // trim para remover espaços em branco no início e no fim
        public SentimentRequest {
                text = text.trim();
        }
}