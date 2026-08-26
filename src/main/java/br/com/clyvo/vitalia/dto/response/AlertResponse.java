package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.enums.AlertStatus;
import br.com.clyvo.vitalia.enums.AlertType;
import br.com.clyvo.vitalia.enums.RiskLevel;
import java.time.LocalDateTime;

public record AlertResponse(
        Long id,
        AlertType type,
        String message,
        RiskLevel riskLevel,
        AlertStatus status,
        LocalDateTime createdAt,
        LocalDateTime resolvedAt,
        String petName
) {}