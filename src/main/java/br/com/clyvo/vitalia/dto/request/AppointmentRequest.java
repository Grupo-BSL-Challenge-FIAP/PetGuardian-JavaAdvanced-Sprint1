package br.com.clyvo.vitalia.dto.request;

import br.com.clyvo.vitalia.enums.AppointmentStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record AppointmentRequest(
        @NotNull(message = "A data da consulta é obrigatória")
        LocalDateTime appointmentDate,

        @NotBlank(message = "O motivo da consulta é obrigatório")
        String reason,

        String diagnosis,

        String recommendation,

        @NotNull(message = "O status é obrigatório")
        AppointmentStatus status,

        @NotNull(message = "O ID do pet é obrigatório")
        Long petId,

        @NotNull(message = "O ID do veterinário é obrigatório")
        Long veterinarianId
) {}