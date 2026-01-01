package com.hackathon.SentimentAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
// Timeout de 5 segundos para conexões e leituras
    private static final int TIMEOUT = 5000;

// Configuração do RestTemplate com timeouts personalizados
    @Bean
    public RestTemplate restTemplate() {

        SimpleClientHttpRequestFactory factory =
                new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(TIMEOUT);
        factory.setReadTimeout(TIMEOUT);

        return new RestTemplate(factory);
    }
}