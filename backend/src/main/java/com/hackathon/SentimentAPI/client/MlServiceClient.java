package com.hackathon.SentimentAPI.client;

import com.hackathon.SentimentAPI.dto.MlServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
public class MlServiceClient {
        // Log para monitoramento e depuração (podendo ser removido posteriormente)
        private static final Logger log = LoggerFactory.getLogger(MlServiceClient.class);

        private final RestTemplate restTemplate;
        private final String mlServiceUrl;

        // Injeção de dependência via construtor
        public MlServiceClient(
                        RestTemplate restTemplate,
                        @Value("${ml.service.url}") String mlServiceUrl) {
                this.restTemplate = restTemplate;
                this.mlServiceUrl = mlServiceUrl;
        }

        // Método para chamar o serviço de ML e obter a previsão de sentimento
        public MlServiceResponse predict(String text) {

                Objects.requireNonNull(text, "Texto não pode ser nulo");

                Map<String, String> request = Map.of("text", text);
                // Log de chamada ao serviço de ML para monitoramento (podendo ser removido
                // posteriormente)
                log.info("Chamando serviço de ML em {}", mlServiceUrl);
                try {
                        ResponseEntity<MlServiceResponse> response = restTemplate.postForEntity(
                                        mlServiceUrl,
                                        request,
                                        MlServiceResponse.class);

                        if (!response.getStatusCode().is2xxSuccessful()
                                        || response.getBody() == null) {

                                throw new IllegalStateException(
                                                "Resposta inválida do serviço de ML");
                        }

                        return response.getBody();
                        // Tratamento de exceções na chamada ao serviço de ML
                } catch (RestClientException ex) {
                        log.error("Erro ao chamar serviço de ML", ex);
                        throw new IllegalStateException(
                                        "Serviço de análise de sentimento indisponível");
                }
        }

        // Método para verificar a saúde do serviço de ML
        public void healthCheck() {
                restTemplate.getForObject(
                                mlServiceUrl.replace("/predict", "/"),
                                String.class);
        }

}