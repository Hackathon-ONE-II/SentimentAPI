package com.hackathon.SentimentAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para envio do texto a ser analisado.
 */
@Schema(
        description = "Objeto de requisição para análise de sentimento"
)
public record SentimentRequest(

        @Schema(
                description = "Texto que será analisado para identificar o sentimento",
                example = "O atendimento foi excelente e muito rápido!"
        )
        @NotBlank(message = "{sentiment.text.notBlank}")
        @Size(min = 3, max = 1300, message = "{sentiment.text.size}")
        @Pattern(
                regexp = ".*[a-zA-Z].*",
                message = "O texto deve conter pelo menos uma palavra"
        )
        String text

) {
        // Remove espaços em branco no início e no fim do texto
        public SentimentRequest {
                text = text.trim();
        }
}
