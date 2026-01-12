package com.hackathon.SentimentAPI.infra.security;

import com.hackathon.SentimentAPI.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    /**
     * Filtro executado em TODAS as requisições da aplicação.
     * Responsável por:
     * 1. Ler o token JWT do header Authorization
     * 2. Validar o token
     * 3. Autenticar o usuário no Spring Security
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Recupera o token JWT da requisição
        String tokenJWT = recoverToken(request);

        // Se existir token
        if (tokenJWT != null) {

            // Extrai o email (subject) de dentro do token
            String emailUser = tokenService.getSubject(tokenJWT);

            // Busca o usuário no banco de dados
            var user = repository.findByUsername(emailUser);

            // Se o usuário existir
            if (user != null) {

                // Cria o objeto de autenticação do Spring Security
                var authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                // Salva o usuário autenticado no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continua o fluxo normal da requisição
        filterChain.doFilter(request, response);
    }

    /**
     * Recupera o token JWT do header Authorization.
     * Esperado:
     * Authorization: Bearer <token>
     */
    private String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }
}