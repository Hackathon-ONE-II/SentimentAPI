package com.hackathon.SentimentAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Definicao de perfil para os acessos de usuarios
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Profile {
    ADMIN_ROLE,
    USER_ROLE;

    private String role;
}