package br.com.clyvo.vitalia.dto.response;

import java.time.LocalDateTime;

public record AlertResponse(
        Long id,
        String alertType,
        String message,
        String severity,
        String status,
        LocalDateTime createdAt,
        LocalDateTime resolvedAt,
        Long petId
) {}
