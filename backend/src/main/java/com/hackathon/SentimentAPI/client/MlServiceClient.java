package com.hackathon.SentimentAPI.client;

import com.hackathon.SentimentAPI.dto.MlServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class MlServiceClient {

    @Value("${ml.service.url}")
    private String mlServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public MlServiceResponse predict(String text) {

        Map<String, String> request = Map.of("text", text);

        return restTemplate.postForObject(
                mlServiceUrl,
                request,
                MlServiceResponse.class
        );
    }
}
