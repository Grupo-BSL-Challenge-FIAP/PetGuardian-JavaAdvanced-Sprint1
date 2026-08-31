package br.com.clyvo.vitalia.dto.request;

import jakarta.validation.constraints.NotNull;

public record ClinicalHistoryRequest(
        @NotNull(message = "O ID do pet é obrigatório")
        Long petId,

        @NotNull(message = "O ID do veterinário é obrigatório")
        Long veterinarianId,

        Long appointmentId,

        String diagnosis,

        String observations,

        String treatment
) {}
