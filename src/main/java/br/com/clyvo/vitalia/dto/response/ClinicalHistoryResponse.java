package br.com.clyvo.vitalia.dto.response;

import java.time.LocalDateTime;

public record ClinicalHistoryResponse(
        Long id,
        Long petId,
        Long veterinarianId,
        Long appointmentId,
        LocalDateTime recordDate,
        String diagnosis,
        String observations,
        String treatment
) {}
