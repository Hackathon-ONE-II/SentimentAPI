package com.hackathon.SentimentAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de resposta da análise de sentimento.
 */
@Schema(
        description = "Resultado da análise de sentimento realizada a partir do texto enviado"
)
public record SentimentResponse(

        @Schema(
                description = "Sentimento previsto para o texto analisado",
                example = "positivo",
                allowableValues = {"positivo", "negativo", "neutro"}
        )
        String previsao,

        @Schema(
                description = "Probabilidade associada à previsão de sentimento",
                example = "0.92"
        )
        Double probabilidade

) {}
