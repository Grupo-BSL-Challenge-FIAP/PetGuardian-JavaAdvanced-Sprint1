package br.com.clyvo.vitalia.dto.request;

import br.com.clyvo.vitalia.enums.CurrentStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record PetRequest(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "A espécie é obrigatória")
        String species,

        @NotBlank(message = "A raça é obrigatória")
        String breed,

        @NotBlank(message = "O género é obrigatório")
        String gender,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser no passado")
        LocalDate birthDate,

        @Positive(message = "O peso deve ser um valor positivo")
        Double weight,

        @NotNull(message = "O status atual é obrigatório")
        CurrentStatus currentStatus,

        @NotNull(message = "O ID do responsável é obrigatório")
        Long responsibleId,

        @NotNull(message = "O ID do veterinário é obrigatório")
        Long veterinarianId
) {}