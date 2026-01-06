package com.hackathon.SentimentAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record SentimentRequest(
        // validação para o campo text min e max size
        @NotBlank(message = "Texto não pode ser vazio")
        @Size(min = 5, max = 1300, message = "Texto deve ter pelo menos 3 caracteres e no máximo 1300 caracteres")
        @Pattern(
                regexp = ".*[a-zA-Z].*",
                message = "O texto deve conter pelo menos uma palavra"
        )
        String text

) {
        // trim para remover espaços em branco no início e no fim
        public SentimentRequest {
                if (text != null)
                text = text.trim();
        }
}