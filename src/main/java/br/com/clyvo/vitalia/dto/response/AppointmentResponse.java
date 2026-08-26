package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.enums.AppointmentStatus;
import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        LocalDateTime appointmentDate,
        String reason,
        String diagnosis,
        String recommendation,
        AppointmentStatus status,
        LocalDateTime createdAt,
        String petName,
        String veterinarianName
) {}