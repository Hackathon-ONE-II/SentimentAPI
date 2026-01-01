package com.hackathon.SentimentAPI.dto;

public record SentimentResponse(
        String previsao,
        Double probabilidade
) {}
