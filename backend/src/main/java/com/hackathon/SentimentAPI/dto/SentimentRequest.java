package com.hackathon.SentimentAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SentimentRequest {

    @NotBlank(message = "O texto é obrigatório")
    @Size(min = 5, message = "O texto deve ter pelo menos 5 caracteres")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
