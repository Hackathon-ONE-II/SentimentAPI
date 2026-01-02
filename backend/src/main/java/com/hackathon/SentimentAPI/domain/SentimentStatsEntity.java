package com.hackathon.SentimentAPI.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sentiment_stats")
public class SentimentStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int total;
    private int positivos;
    private int negativos;
    private int neutros;

    private int success;
    private int fallback;

    private LocalDateTime lastUpdate;

    // JPA exige construtor vazio
    protected SentimentStatsEntity() {}

    public SentimentStatsEntity(
            int total,
            int positivos,
            int negativos,
            int neutros,
            int success,
            int fallback,
            LocalDateTime lastUpdate
    ) {
        this.total = total;
        this.positivos = positivos;
        this.negativos = negativos;
        this.neutros = neutros;
        this.success = success;
        this.fallback = fallback;
        this.lastUpdate = lastUpdate;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPositivos() {
        return positivos;
    }

    public void setPositivos(int positivos) {
        this.positivos = positivos;
    }

    public int getNegativos() {
        return negativos;
    }

    public void setNegativos(int negativos) {
        this.negativos = negativos;
    }

    public int getNeutros() {
        return neutros;
    }

    public void setNeutros(int neutros) {
        this.neutros = neutros;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFallback() {
        return fallback;
    }

    public void setFallback(int fallback) {
        this.fallback = fallback;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
