package br.com.clyvo.vitalia.dto.response;

import java.time.LocalDateTime;

public record ClinicalHistoryResponse(
        Long id,
        Double temperature,
        Integer heartRate,
        String activityLevel,
        Double healthScore,
        String description,
        String observations,
        LocalDateTime recordedAt,
        String petName
) {}