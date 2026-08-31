package br.com.clyvo.vitalia.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlertRequest(
        @NotBlank(message = "O tipo do alerta é obrigatório")
        String alertType,

        @NotBlank(message = "A mensagem é obrigatória")
        String message,

        @NotBlank(message = "A severidade é obrigatória")
        String severity,

        String status,

        @NotNull(message = "O ID do pet é obrigatório")
        Long petId
) {}
