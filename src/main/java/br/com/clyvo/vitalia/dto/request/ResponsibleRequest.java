package br.com.clyvo.vitalia.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ResponsibleRequest(
        @Schema(example = "Astrogildo Celeste de Souza")
        @NotBlank(message = "O nome completo é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String fullName,

        @Schema(example = "123.456.789-00")
        @NotBlank(message = "O CPF é obrigatório")
        @CPF
        String cpf,

        @Schema(example = "1995-05-15")
        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve ser uma data no passado")
        LocalDate dateOfBirth,

        @Schema(example = "(11) 98765-4321")
        @NotBlank(message = "O número de telefone é obrigatório")
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve seguir o formato (11) 99999-9999")
        String phoneNumber,

        @Schema(example = "Rua das Flores, 123 - São Paulo")
        @NotBlank(message = "O endereço é obrigatório")
        @Size(max = 255, message = "O endereço é muito longo")
        String address,

        @Schema(example = "1")
        @NotNull(message = "O ID da conta vinculada é obrigatório")
        Long accountId
) {}