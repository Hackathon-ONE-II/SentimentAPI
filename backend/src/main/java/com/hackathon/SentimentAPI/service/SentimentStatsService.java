package com.hackathon.SentimentAPI.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SentimentStatsService {

    private final Map<String, Integer> stats = new ConcurrentHashMap<>();

    public void registrar(String previsao) {
        stats.merge(previsao, 1, Integer::sum);
    }

    public Map<String, Integer> getStats() {
        return stats;
    }
}
