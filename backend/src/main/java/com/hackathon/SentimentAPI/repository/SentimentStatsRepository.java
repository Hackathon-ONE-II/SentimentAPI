package com.hackathon.SentimentAPI.repository;

import com.hackathon.SentimentAPI.domain.SentimentStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositório JPA responsável por persistir e recuperar métricas de sentimento.
@Repository
public interface SentimentStatsRepository
        extends JpaRepository<SentimentStatsEntity, Long> {
}
