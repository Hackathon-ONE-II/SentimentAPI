package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;
import com.hackathon.SentimentAPI.service.SentimentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sentiment")
public class SentimentController {
    // Logger para monitoramento e depuração
    private static final Logger log =
            LoggerFactory.getLogger(SentimentController.class);

    private final SentimentService sentimentService;
    // Injeção de dependência via construtor
    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }
    // Endpoint para análise de sentimento
    @PostMapping("/analyze")
    public ResponseEntity<SentimentResponse> analisar(
            @RequestBody
            @Valid
            SentimentRequest request
    ) {

        // Log de início da análise para monitoramento(podendo ser removido posteriormente)
        log.info("Iniciando análise de sentimento");

        SentimentResponse response = sentimentService.analisar(request);
        return ResponseEntity.ok(response);
    }
}