package com.ceox.ceox_data_service.service;

import com.ceox.ceox_data_service.model.WeeklyDataDTO;
import com.ceox.ceox_data_service.model.WeeklyReportResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiReportService {

    private final WebClient webClient;

    public GeminiReportService(@Value("${ceox.intelligent.url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public WeeklyReportResponse generateFullReport(WeeklyDataDTO data) {
        return webClient.post()
                .uri("/api/summary")
                .bodyValue(data)
                .retrieve()
                .bodyToMono(WeeklyReportResponse.class)
                .block();
    }
}
