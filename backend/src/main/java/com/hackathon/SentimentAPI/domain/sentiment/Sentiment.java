package com.hackathon.SentimentAPI.domain.sentiment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sentiment {
    private String previsao;
    private Double probabilidade;
}
