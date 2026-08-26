package br.com.clyvo.vitalia.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record VeterinarianRequest(
        @Schema(example = "Dr. Ricardo Santos")
        @NotBlank(message = "O nome completo é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String fullName,

        @Schema(example = "123.456.789-00")
        @NotBlank(message = "O CPF é obrigatório")
        @CPF
        String cpf,

        @Schema(example = "CRMV-SP 12345")
        @NotBlank(message = "O CRMV é obrigatório para veterinários")
        @Pattern(regexp = "CRMV-[A-Z]{2}\\s\\d+", message = "O CRMV deve seguir o formato 'CRMV-UF 00000'")
        String crmv,

        @Schema(example = "Cardiologia")
        @NotBlank(message = "A especialidade é obrigatória")
        @Size(min = 2, max = 50, message = "A especialidade deve ter entre 2 e 50 caracteres")
        String speciality,

        @Schema(example = "2")
        @NotNull(message = "O ID da conta vinculada é obrigatório")
        Long accountId
) {}