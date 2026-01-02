package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.dto.SentimentStatsResponse;
import com.hackathon.SentimentAPI.service.SentimentStatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller responsável por expor métricas
 * agregadas da análise de sentimento.
 */
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final SentimentStatsService statsService;

    // Injeção de dependência via construtor
    public StatsController(SentimentStatsService statsService) {
        this.statsService = statsService;
    }

    /**
     * Endpoint LEGADO
     * Mantido para compatibilidade com clientes existentes.
     * Retorna estatísticas simples em formato Map.
     */
    @GetMapping
    public Map<String, Integer> stats() {
        return statsService.getStats();
    }

    /**
     * Endpoint NOVO (evolução)
     * Retorna métricas completas e estruturadas.
     * Ideal para dashboards e persistência futura.
     */
    @GetMapping("/v2")
    public SentimentStatsResponse statsV2() {
        return statsService.getStatsDTO();
    }
}
