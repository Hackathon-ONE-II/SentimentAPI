package com.hackathon.SentimentAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Profile {
    ADMIN_ROLE,
    USER_ROLE;

    private String role;
}