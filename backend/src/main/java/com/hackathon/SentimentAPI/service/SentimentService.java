package com.hackathon.SentimentAPI.service;

import org.springframework.stereotype.Service;
import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;

@Service
public class SentimentService {

    public SentimentResponse analisar(SentimentRequest request) {

        // TEMPORAL (simulação de regra de negócio de classificação de sentimento da revin)
        return new SentimentResponse("Positivo", 0.95);
    }
}
