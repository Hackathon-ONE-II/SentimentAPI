package com.hackathon.SentimentAPI.dto;

import java.time.LocalDateTime;


 // DTO que representa métricas agregadas da análise de sentimento.
 
public record SentimentStatsResponse(

        long totalRequests,
        long positivos,
        long negativos,
        long neutros,

        long success,
        long fallback,

        LocalDateTime lastUpdated
) {}
