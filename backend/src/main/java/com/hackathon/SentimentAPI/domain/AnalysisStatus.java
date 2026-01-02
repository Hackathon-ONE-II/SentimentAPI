package com.hackathon.SentimentAPI.domain;


 // Representa o status da análise de sentimento.
 // Serve para padronizar respostas e facilitar integração com outros serviços ou frontend.
 
public enum AnalysisStatus {

    // Análise realizada com sucesso via serviço de ML
    SUCCESS,

    // Resposta gerada via fallback (ML indisponível)
    FALLBACK,

    // Erro inesperado durante a análise (uso futuro)
    ERROR
}
