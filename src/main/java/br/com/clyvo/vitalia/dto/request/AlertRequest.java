package br.com.clyvo.vitalia.dto.request;

import br.com.clyvo.vitalia.enums.AlertStatus;
import br.com.clyvo.vitalia.enums.AlertType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlertRequest(
        @NotBlank(message = "O tipo do alerta é obrigatório")
        AlertType alertType,

        @NotBlank(message = "A mensagem é obrigatória")
        String message,

        @NotBlank(message = "A severidade é obrigatória")
        String severity,

        AlertStatus status,

        @NotNull(message = "O ID do pet é obrigatório")
        Long petId
) {}
