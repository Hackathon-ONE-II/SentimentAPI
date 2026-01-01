package com.hackathon.SentimentAPI.service;

import com.hackathon.SentimentAPI.client.MlServiceClient;
import com.hackathon.SentimentAPI.dto.MlServiceResponse;
import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SentimentService {
// Log para monitoramento e depuração (podendo ser removido posteriormente)
    private static final Logger log =
            LoggerFactory.getLogger(SentimentService.class);

    private final MlServiceClient mlServiceClient;
    private final SentimentStatsService statsService;

    public SentimentService(
            MlServiceClient mlServiceClient,
            SentimentStatsService statsService
    ) {
        this.mlServiceClient = mlServiceClient;
        this.statsService = statsService;
    }
    public SentimentResponse analisar(SentimentRequest request) {
// Validação básica do request
        Objects.requireNonNull(request, "SentimentRequest não pode ser nulo");

        MlServiceResponse mlResponse =
                mlServiceClient.predict(request.text());
// Verificação da resposta do serviço de ML
        if (mlResponse == null) {
            throw new IllegalStateException(
                    "Resposta nula do serviço de ML"
            );
        }
// Extração dos dados relevantes da resposta
        String previsao = mlResponse.previsao();
        double probabilidade = mlResponse.probabilidade();
// Registro da estatística de sentimento e tratamento de exceções
        try {
            statsService.registrar(previsao);
        } catch (Exception e) {
            log.warn("Falha ao registrar estatística de sentimento", e);
        }
// Log de conclusão da análise para monitoramento (podendo ser removido posteriormente)
        log.info("Análise concluída com sucesso. Sentimento: {}", previsao);

        return new SentimentResponse(previsao, probabilidade);
    }
}