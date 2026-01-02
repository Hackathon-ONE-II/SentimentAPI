package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.client.MlServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
// Controller responsável por verificar se a aplicação está viva
// e se o serviço de Machine Learning está respondendo corretamente
@RestController
public class HealthController {

    // Cliente que se comunica com o microserviço de ML
    private final MlServiceClient mlServiceClient;

    // Injeção de dependência via construtor
    public HealthController(MlServiceClient mlServiceClient) {
        this.mlServiceClient = mlServiceClient;
    }

    // Endpoint simples para monitoramento da saúde da aplicação
    @GetMapping("/health")
    public Map<String, String> health() {

        // Status do serviço de ML
        String mlStatus;

        try {
            // Tenta chamar o serviço de ML
            mlServiceClient.healthCheck();
            mlStatus = "UP";
        } catch (Exception e) {
            // Caso ocorra erro, consideramos o serviço indisponível
            mlStatus = "DOWN";
        }

        // Retorno padrão do health check
        return Map.of(
                "status", "UP",
                "mlService", mlStatus
        );
    }
}
