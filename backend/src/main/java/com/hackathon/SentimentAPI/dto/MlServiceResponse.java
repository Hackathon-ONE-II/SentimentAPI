package com.hackathon.SentimentAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MlServiceResponse(

        // Texto após pré-processamento (uso interno / debug / auditoria)
        String textoProcessado,

        // Sentimento previsto pelo modelo
        String previsao,

        // Grau de confiança da previsão
        Double probabilidade

) {}
