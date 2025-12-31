package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.service.SentimentStatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final SentimentStatsService statsService;

    public StatsController(SentimentStatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public Map<String, Integer> stats() {
        return Map.of(
                "total", statsService.getTotal(),
                "positivos", statsService.getPositivos(),
                "negativos", statsService.getNegativos(),
                "neutros", statsService.getNeutros()
        );
    }
}
