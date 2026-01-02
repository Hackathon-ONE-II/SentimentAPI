package com.hackathon.SentimentAPI.dto;

public record SentimentResponse(

        // Sentimento final analisado
        String previsao,

        // Probabilidade/confiança da análise
        Double probabilidade

) {}
