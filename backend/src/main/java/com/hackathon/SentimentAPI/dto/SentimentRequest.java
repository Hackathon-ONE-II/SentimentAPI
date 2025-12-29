package com.hackathon.SentimentAPI.dto;

public record SentimentRequest(
    String texto,      
    String idioma      
) {
}