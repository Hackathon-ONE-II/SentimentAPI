package com.hackathon.SentimentAPI.controller;

import com.hackathon.SentimentAPI.service.SentimentStatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller responsável por fornecer estatísticas das análises de sentimento.
 */
@Tag(
        name = "Estatísticas",
        description = "Endpoints para consulta de estatísticas das análises de sentimentos realizadas"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final SentimentStatsService statsService;

    public StatsController(SentimentStatsService statsService) {
        this.statsService = statsService;
    }

    /**
     * Retorna estatísticas agregadas das análises de sentimento.
     *
     * @return mapa contendo o tipo de sentimento e sua quantidade
     */
    @Operation(
            summary = "Obter estatísticas de sentimentos",
            description = "Retorna a quantidade de análises realizadas para cada tipo de sentimento (positivo, negativo e neutro)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estatísticas retornadas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{ \"positivo\": 12, \"negativo\": 5, \"neutro\": 8 }"
                            )
                    )
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
    @GetMapping
    public Map<String, Integer> stats() {
        return statsService.getStats();
    }
}
