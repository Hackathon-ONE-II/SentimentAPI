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

        // Log para monitoramento e depuração
        private static final Logger log = LoggerFactory.getLogger(MlServiceClient.class);

        private final RestTemplate restTemplate;
        private final String mlServiceUrl;

        public MlServiceClient(
                        RestTemplate restTemplate,
                        @Value("${ml.service.url}") String mlServiceUrl) {
                this.restTemplate = restTemplate;
                this.mlServiceUrl = mlServiceUrl;
        }

        // Chama o serviço de ML para prever o sentimento.
        // Este método é blindado contra falhas externas.
        public MlServiceResponse predict(String text) {

                Objects.requireNonNull(text, "Texto não pode ser nulo");

                Map<String, String> request = Map.of("text", text);

                log.info("Chamando serviço de ML em {}", mlServiceUrl);

                try {
                        ResponseEntity<MlServiceResponse> response = restTemplate.postForEntity(
                                        mlServiceUrl,
                                        request,
                                        MlServiceResponse.class);

                        if (!response.getStatusCode().is2xxSuccessful()
                                        || response.getBody() == null) {

                                log.warn("Resposta inválida do serviço de ML");
                                return fallback();
                        }

                        return response.getBody();

                } catch (RestClientException ex) {
                        log.error("Erro ao chamar serviço de ML", ex);
                        return fallback();
                }
        }

        // Fallback padrão caso o serviço de ML esteja indisponível.
        private MlServiceResponse fallback() {
                return new MlServiceResponse(
                                null,
                                "Neutro",
                                0.0);
        }

        // Verifica se o serviço de ML está acessível.
        // Pode ser usado futuramente em health checks.
        public boolean healthCheck() {
                try {
                        restTemplate.getForObject(
                                        mlServiceUrl.replace("/predict", "/"),
                                        String.class);
                        return true;
                } catch (Exception e) {
                        return false;
                }
        }
}
