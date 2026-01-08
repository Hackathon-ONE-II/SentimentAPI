package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;
import com.hackathon.SentimentAPI.service.SentimentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável pela análise de sentimentos.
 */
@Tag(
        name = "Análise de Sentimento",
        description = "Endpoints para análise e classificação de sentimentos em textos"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/sentiment")
public class SentimentController {

    // Logger para monitoramento e depuração
    private static final Logger log =
            LoggerFactory.getLogger(SentimentController.class);

    private final SentimentService sentimentService;

    // Injeção de dependência via construtor
    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    /**
     * Endpoint responsável por analisar o sentimento de um texto.
     *
     * @param request objeto contendo o texto a ser analisado
     * @return sentimento identificado e nível de confiança
     */
    @Operation(
            summary = "Analisar sentimento de um texto",
            description = "Recebe um texto e retorna a classificação de sentimento (positivo, negativo ou neutro)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Análise realizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SentimentResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos enviados na requisição",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado (JWT ausente ou inválido)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content
            )
    })
    @PostMapping("/analyze")
    public ResponseEntity<SentimentResponse> analisar(
            @RequestBody
            @Valid
            SentimentRequest request
    ) {

        // Log de início da análise para monitoramento
        log.info("Iniciando análise de sentimento");

        SentimentResponse response = sentimentService.analisar(request);
        return ResponseEntity.ok(response);
    }
}
