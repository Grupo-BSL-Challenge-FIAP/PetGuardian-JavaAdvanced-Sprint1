package br.com.clyvo.vitalia.dto.request;

import br.com.clyvo.vitalia.enums.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentRequest(
        @NotNull(message = "A data da consulta é obrigatória")
        LocalDateTime appointmentDate,

        AppointmentStatus status,

        String notes,

        @NotNull(message = "O ID do pet é obrigatório")
        Long petId,

        @NotNull(message = "O ID do veterinário é obrigatório")
        Long veterinarianId
) {}
