package com.hackathon.SentimentAPI.dto;

import java.util.List;

import com.hackathon.SentimentAPI.domain.AnalysisStatus;


 // DTO de resposta da análise de sentimento.
 
public record SentimentResponse(

        // Resultado previsto pelo modelo
        String previsao,

        // Grau de confiança da previsão
        Double probabilidade,

        // Status da análise
        AnalysisStatus status,

        // Principais palavras que influenciaram a análise
        List<String> principais_palavras
) {}
