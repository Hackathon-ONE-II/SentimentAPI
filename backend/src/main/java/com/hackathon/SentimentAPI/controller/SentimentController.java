package com.hackathon.SentimentAPI.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;

@RestController
@RequestMapping("/sentiment")
public class SentimentController {

    @PostMapping
    public SentimentResponse analisar(@RequestBody @Valid SentimentRequest request) {

        // TEMPORAL (simulación)
        return new SentimentResponse("Positivo", 0.95);
    }
}
