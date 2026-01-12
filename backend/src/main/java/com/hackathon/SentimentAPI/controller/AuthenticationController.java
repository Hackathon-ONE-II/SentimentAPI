package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.domain.User;
import com.hackathon.SentimentAPI.dto.TokenJWTResponse;
import com.hackathon.SentimentAPI.dto.AuthenticationRequest;
import com.hackathon.SentimentAPI.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Controller responsavel pela autenticacao e autorizacao do login
 */
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    // TEMPORÁRIO: Apenas para testar se o endpoint está protegido

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/gerar-senha")
    public String gerarSenha() {
        return passwordEncoder.encode("123456");
    }

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity runLogin(@RequestBody @Valid AuthenticationRequest request) {
        try {
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.username(),
                    request.password());
            Authentication authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.getToken((User) Objects.requireNonNull(authentication.getPrincipal()));
            return ResponseEntity.ok(new TokenJWTResponse(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}