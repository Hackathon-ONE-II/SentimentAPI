package com.hackathon.SentimentAPI.service;

import com.hackathon.SentimentAPI.domain.SentimentStatsEntity;
import com.hackathon.SentimentAPI.dto.SentimentStatsResponse;
import com.hackathon.SentimentAPI.repository.SentimentStatsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Serviço responsável por gerenciar métricas
 * de sentimento com persistência em banco.
 */
@Service
public class SentimentStatsService {

    private final SentimentStatsRepository repository;

    public SentimentStatsService(SentimentStatsRepository repository) {
        this.repository = repository;
    }

    /**
     * Recupera o registro único de métricas.
     * Caso não exista, cria um novo.
     */
    private SentimentStatsEntity getStatsEntity() {
        return repository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> repository.save(new SentimentStatsEntity()));
    }

    /**
     * Método mantido para compatibilidade
     * com o controller antigo.
     */
    public Map<String, Integer> getStats() {
        SentimentStatsEntity stats = getStatsEntity();

        return Map.of(
                "total", stats.getTotal(),
                "positivo", stats.getPositivos(),
                "negativo", stats.getNegativos(),
                "neutro", stats.getNeutros()
        );
    }

    /**
     * Retorna métricas completas via DTO.
     */
    public SentimentStatsResponse getStatsDTO() {
        SentimentStatsEntity stats = getStatsEntity();

        return new SentimentStatsResponse(
                stats.getTotal(),
                stats.getPositivos(),
                stats.getNegativos(),
                stats.getNeutros(),
                stats.getSuccess(),
                stats.getFallback(),
                stats.getLastUpdate()
        );
    }

    /**
     * Registra uma nova análise de sentimento.
     */
    public synchronized void registrar(
            String previsao,
            boolean isFallback
    ) {
        SentimentStatsEntity stats = getStatsEntity();

        stats.setTotal(stats.getTotal() + 1);
        stats.setLastUpdate(LocalDateTime.now());

        if (isFallback) {
            stats.setFallback(stats.getFallback() + 1);
        } else {
            stats.setSuccess(stats.getSuccess() + 1);
        }

        if ("Positivo".equalsIgnoreCase(previsao)) {
            stats.setPositivos(stats.getPositivos() + 1);
        } else if ("Negativo".equalsIgnoreCase(previsao)) {
            stats.setNegativos(stats.getNegativos() + 1);
        } else {
            stats.setNeutros(stats.getNeutros() + 1);
        }

        repository.save(stats);
    }
}
