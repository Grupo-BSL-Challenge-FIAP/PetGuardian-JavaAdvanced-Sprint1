package br.com.clyvo.vitalia.dto.request;

import jakarta.validation.constraints.*;

public record ClinicalHistoryRequest(

        @NotNull
        Double temperature,

        @NotNull
        Integer heartRate,

        @NotBlank
        String activityLevel,

        @Min(0) @Max(100)
        Double healthScore,

        String description,

        String observations,

        @NotNull
        Long petId
) {}