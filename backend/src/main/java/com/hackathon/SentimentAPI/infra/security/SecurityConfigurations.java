package com.hackathon.SentimentAPI.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Classe responsável por TODA a configuração de segurança da aplicação.
 * Aqui definimos:
 * - CORS
 * - Autenticação
 * - Autorização
 * - Política de sessão
 * - Criptografia de senha
 */
@Configuration
public class SecurityConfigurations {

    /**
     * 🔐 Configuração principal do Spring Security
     * Define quais endpoints são públicos e quais são protegidos.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 👉 API REST é stateless (não usa sessão)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 👉 Habilita CORS usando a configuração definida abaixo
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // 👉 Desabilita CSRF (necessário para APIs REST)
            .csrf(csrf -> csrf.disable())

            // 👉 Regras de acesso aos endpoints
            .authorizeHttpRequests(auth -> auth
                // Endpoint de login (público)
                .requestMatchers(HttpMethod.POST, "/login").permitAll()

                // Endpoint temporário para gerar senha criptografada (público)
                .requestMatchers(HttpMethod.GET, "/login/gerar-senha").permitAll()

                // Health check (pode ser público)
                .requestMatchers(HttpMethod.GET, "/health").permitAll()

                // Qualquer outro endpoint exige autenticação
                .anyRequest().authenticated()
            );

        return http.build();
    }

    /**
     * 🌍 Configuração global de CORS
     * Permite que o frontend se comunique com o backend.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 🔹 Origem permitida (frontend)
        config.setAllowedOrigins(List.of("http://localhost:3000"));

        // 🔹 Métodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 🔹 Headers permitidos (inclui Authorization para JWT)
        config.setAllowedHeaders(List.of("*"));

        // 🔹 Permite envio de credenciais (Authorization: Bearer TOKEN)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    /**
     * 🔑 Bean responsável por criptografar senhas usando BCrypt.
     * O mesmo encoder é usado para:
     * - salvar senha no banco
     * - validar senha no login
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 🔐 AuthenticationManager
     * Responsável por validar usuário e senha no processo de login.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
