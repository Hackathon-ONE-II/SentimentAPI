package com.hackathon.SentimentAPI.service;

import com.hackathon.SentimentAPI.client.MlServiceClient;
import com.hackathon.SentimentAPI.dto.MlServiceResponse;
import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {

    private final MlServiceClient mlServiceClient;
    private final SentimentStatsService statsService;

    public SentimentService(MlServiceClient mlServiceClient, SentimentStatsService statsService) {
        this.mlServiceClient = mlServiceClient;
        this.statsService = statsService;
    }

    public SentimentResponse analisar(SentimentRequest request) {

        if (request.getText() == null || request.getText().isBlank()) {
            throw new IllegalArgumentException("Texto não pode ser vazio");
        }

        MlServiceResponse mlResponse = mlServiceClient.predict(request.getText());

        statsService.registrar(mlResponse.getPrevisao());

        return new SentimentResponse(
                mlResponse.getPrevisao(),
                mlResponse.getProbabilidade()
        );
    }
}

