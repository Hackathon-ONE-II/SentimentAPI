package com.hackathon.SentimentAPI.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Entidade responsável por armazenar métricas agregadas de análises de sentimento.
// Esta tabela será usada futuramente para monitoramento, dashboards e auditoria.
@Entity
@Table(name = "sentiment_stats")
public class SentimentStatsEntity {

    /**
     * Identificador único do registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Total de análises realizadas.
     */
    @Column(nullable = false)
    private int total;

    /**
     * Total de sentimentos positivos.
     */
    @Column(nullable = false)
    private int positivos;

    /**
     * Total de sentimentos negativos.
     */
    @Column(nullable = false)
    private int negativos;

    /**
     * Total de sentimentos neutros.
     */
    @Column(nullable = false)
    private int neutros;

    /**
     * Total de análises realizadas com sucesso (ML respondeu).
     */
    @Column(nullable = false)
    private int success;

    /**
     * Total de análises em fallback (ML indisponível).
     */
    @Column(nullable = false)
    private int fallback;

    /**
     * Data da última atualização das métricas.
     */
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    /* ===== Construtor padrão exigido pelo JPA ===== */
    public SentimentStatsEntity() {
    }

    /* ===== Getters e Setters ===== */

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




