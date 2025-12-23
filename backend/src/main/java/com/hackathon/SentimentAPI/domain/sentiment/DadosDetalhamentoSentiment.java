package com.hackathon.SentimentAPI.domain.sentiment;

public record DadosDetalhamentoSentiment(String previsao, Double probabilidade) {
    public DadosDetalhamentoSentiment(Sentiment sentiment) {
        this(sentiment.getPrevisao(), sentiment.getProbabilidade());
    }
}
