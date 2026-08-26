package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.enums.Role;
import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String email,
        Role role,
        Boolean active,
        LocalDateTime createdAt
) {}