package com.hackathon.SentimentAPI.service;

import com.hackathon.SentimentAPI.client.MlServiceClient;
import com.hackathon.SentimentAPI.dto.MlServiceResponse;
import org.springframework.stereotype.Service;
import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;

@Service
public class SentimentService {

    private final MlServiceClient mlServiceClient;

    public SentimentService(MlServiceClient mlServiceClient) {
        this.mlServiceClient = mlServiceClient;
    }

    public SentimentResponse analisar(SentimentRequest request) {

        MlServiceResponse mlResponse = mlServiceClient.predict(request.getText());

        return new SentimentResponse(
                mlResponse.getPrevisao(),
                mlResponse.getProbabilidade()
        );
    }
}
