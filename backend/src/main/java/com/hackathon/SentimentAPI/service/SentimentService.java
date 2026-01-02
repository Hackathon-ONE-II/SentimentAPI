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

    // Log para monitoramento e depuração
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

    /**
     * Realiza a análise de sentimento de um texto.
     * Caso o serviço de ML esteja indisponível,
     * aplica fallback sem quebrar o backend.
     */
    public SentimentResponse analisar(SentimentRequest request) {

        // Validação básica do request
        Objects.requireNonNull(request, "SentimentRequest não pode ser nulo");

        // Chamada ao serviço de Machine Learning
        MlServiceResponse mlResponse =
                mlServiceClient.predict(request.text());

        // 🔹 Fallback em caso de falha do serviço de ML
        if (mlResponse == null) {
            log.warn("Serviço de ML indisponível. Aplicando fallback.");

            // Retorno padrão quando ML falha
            return new SentimentResponse("Indefinido", 0.0);
        }

        // Extração dos dados da resposta do ML
        String previsao = mlResponse.previsao();
        double probabilidade = mlResponse.probabilidade();

        // Registro de estatísticas (não pode quebrar o fluxo)
        try {
            statsService.registrar(previsao);
        } catch (Exception e) {
            log.warn("Falha ao registrar estatística de sentimento", e);
        }

        log.info("Análise concluída com sucesso. Sentimento: {}", previsao);

        return new SentimentResponse(previsao, probabilidade);
    }
}
