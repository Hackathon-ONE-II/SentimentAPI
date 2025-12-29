package com.hackathon.SentimentAPI.dto;


public class SentimentResponse {

    private String previsao;
    private Double probabilidade;

    public SentimentResponse(String previsao, Double probabilidade) {
        this.previsao = previsao;
        this.probabilidade = probabilidade;
    }

    public String getPrevisao() {
        return previsao;
    }

    public Double getProbabilidade() {
        return probabilidade;
    }
}
