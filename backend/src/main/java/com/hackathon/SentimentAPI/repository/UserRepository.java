package com.hackathon.SentimentAPI.repository;

import com.hackathon.SentimentAPI.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

// Repository responsavel por verificar username na tabela de users
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String Username);
}
