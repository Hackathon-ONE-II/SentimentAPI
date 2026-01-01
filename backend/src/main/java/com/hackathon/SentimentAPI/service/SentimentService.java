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

        MlServiceResponse mlResponse = mlServiceClient.predict(request.text());

        statsService.registrar(mlResponse.previsao());

        return new SentimentResponse(
                mlResponse.previsao(),
                mlResponse.probabilidade()
        );
    }
}
