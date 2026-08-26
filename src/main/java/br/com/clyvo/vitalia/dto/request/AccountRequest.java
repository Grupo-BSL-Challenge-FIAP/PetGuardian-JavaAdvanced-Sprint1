package br.com.clyvo.vitalia.dto.request;

import br.com.clyvo.vitalia.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record AccountRequest(
        @Schema(example = "usuario@petguardian.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        @Pattern(regexp = ".+@.+\\..+", message = "O e-mail deve conter um domínio válido (ex: .com)")
        String email,

        @Schema(example = "Senha@123")
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
                message = "A senha deve conter pelo menos uma letra e um número"
        )
        String password,

        @Schema(example = "TUTOR")
        @NotNull(message = "O papel (role) do usuário é obrigatório")
        Role role,

        @Schema(example = "true")
        @NotNull(message = "O status ativo/inativo deve ser informado")
        Boolean active
) {}