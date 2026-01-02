package com.hackathon.SentimentAPI.service;

import com.hackathon.SentimentAPI.client.MlServiceClient;
import com.hackathon.SentimentAPI.dto.MlServiceResponse;
import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;
import com.hackathon.SentimentAPI.domain.AnalysisStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SentimentService {

        private static final Logger log = LoggerFactory.getLogger(SentimentService.class);

        private final MlServiceClient mlServiceClient;
        private final SentimentStatsService statsService;

        public SentimentService(
                        MlServiceClient mlServiceClient,
                        SentimentStatsService statsService) {
                this.mlServiceClient = mlServiceClient;
                this.statsService = statsService;
        }

        public SentimentResponse analisar(SentimentRequest request) {

                Objects.requireNonNull(
                                request, "SentimentRequest não pode ser nulo");

                // Chamada ao microserviço de ML
                MlServiceResponse mlResponse = mlServiceClient.predict(request.text());

                // FALLBACK: serviço de ML indisponível
                if (mlResponse == null) {
                        log.warn("Serviço de ML indisponível. Aplicando fallback.");

                        return new SentimentResponse(
                                        "Indefinido",
                                        0.0,
                                        AnalysisStatus.FALLBACK);
                }

                // Extração segura dos dados retornados pelo ML
                String previsao = mlResponse.previsao();
                double probabilidade = mlResponse.probabilidade();

                // Registro estatístico apenas se ML respondeu corretamente
                try {
                        statsService.registrar(previsao);
                } catch (Exception e) {
                        log.warn(
                                        "Falha ao registrar estatística de sentimento",
                                        e);
                }

                log.info(
                                "Análise concluída com sucesso. Sentimento: {}",
                                previsao);

                // SUCESSO
                return new SentimentResponse(
                                previsao,
                                probabilidade,
                                AnalysisStatus.SUCCESS);
        }
}
