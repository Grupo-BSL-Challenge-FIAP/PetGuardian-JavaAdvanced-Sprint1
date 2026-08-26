package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.enums.CurrentStatus;
import java.time.LocalDate;

public record PetResponse(
        Long id,
        String name,
        String species,
        String breed,
        LocalDate birthDate,
        Double weight,
        CurrentStatus currentStatus,
        String responsibleName,
        String veterinarianName
) {}