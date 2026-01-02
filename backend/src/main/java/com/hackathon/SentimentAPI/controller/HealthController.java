package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.client.MlServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller responsável pelos health checks da aplicação
 * e do serviço de Machine Learning.
 */
@RestController
public class HealthController {

    private final MlServiceClient mlServiceClient;

    public HealthController(MlServiceClient mlServiceClient) {
        this.mlServiceClient = mlServiceClient;
    }

    /**
     * Health check geral da API.
     */
    @GetMapping("/health")
    public Map<String, String> health() {

        boolean mlUp = mlServiceClient.healthCheck();

        return Map.of(
                "status", "UP",
                "mlService", mlUp ? "UP" : "DOWN"
        );
    }

    /**
     * Health check exclusivo do serviço de ML.
     */
    @GetMapping("/health/ml")
    public Map<String, String> healthMl() {

        boolean mlUp = mlServiceClient.healthCheck();

        return Map.of(
                "mlService", mlUp ? "UP" : "DOWN"
        );
    }
}
