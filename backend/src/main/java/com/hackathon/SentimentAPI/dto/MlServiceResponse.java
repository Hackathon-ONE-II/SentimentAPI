package com.hackathon.SentimentAPI.dto;

public record MlServiceResponse(
        String textoProcessado,
        String previsao,
        Double probabilidade
) {}
