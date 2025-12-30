package com.hackathon.SentimentAPI.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.hackathon.SentimentAPI.dto.SentimentRequest;
import com.hackathon.SentimentAPI.dto.SentimentResponse;
import com.hackathon.SentimentAPI.service.SentimentService;

@RestController
@RequestMapping("/sentiment")
public class SentimentController {

    private final SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @PostMapping
    public SentimentResponse analisar(@RequestBody @Valid SentimentRequest request) {
        return sentimentService.analisar(request);
    }
}
