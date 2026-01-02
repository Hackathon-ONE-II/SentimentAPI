package com.hackathon.SentimentAPI.service;

import com.hackathon.SentimentAPI.domain.SentimentStatsEntity;
import com.hackathon.SentimentAPI.dto.SentimentStatsResponse;
import com.hackathon.SentimentAPI.repository.SentimentStatsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class SentimentStatsService {

    private final SentimentStatsRepository repository;

    public SentimentStatsService(SentimentStatsRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtém (ou cria) o registro único de métricas.
     */
    private SentimentStatsEntity getStatsEntity() {
        return repository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() ->
                        repository.save(
                                new SentimentStatsEntity(
                                        0, 0, 0, 0,
                                        0, 0,
                                        LocalDateTime.now()
                                )
                        )
                );
    }

    // ENDPOINT LEGADO
    public Map<String, Integer> getStats() {
        SentimentStatsEntity e = getStatsEntity();

        return Map.of(
                "total", e.getTotal(),
                "positivo", e.getPositivos(),
                "negativo", e.getNegativos(),
                "neutro", e.getNeutros()
        );
    }

    // ENDPOINT NOVO
    public SentimentStatsResponse getStatsDTO() {
        SentimentStatsEntity e = getStatsEntity();

        return new SentimentStatsResponse(
                e.getTotal(),
                e.getPositivos(),
                e.getNegativos(),
                e.getNeutros(),
                e.getSuccess(),
                e.getFallback(),
                e.getLastUpdate()
        );
    }

    /**
     * Registro persistente das métricas.
     */
    public synchronized void registrar(
            String previsao,
            boolean isFallback
    ) {
        SentimentStatsEntity e = getStatsEntity();

        e.setTotal(e.getTotal() + 1);
        e.setLastUpdate(LocalDateTime.now());

        if (isFallback) {
            e.setFallback(e.getFallback() + 1);
        } else {
            e.setSuccess(e.getSuccess() + 1);
        }

        if ("Positivo".equalsIgnoreCase(previsao)) {
            e.setPositivos(e.getPositivos() + 1);
        } else if ("Negativo".equalsIgnoreCase(previsao)) {
            e.setNegativos(e.getNegativos() + 1);
        } else {
            e.setNeutros(e.getNeutros() + 1);
        }

        repository.save(e);
    }
}
