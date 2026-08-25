package br.com.clyvo.petguardian.dto.response;

import br.com.clyvo.petguardian.enums.Role;

public record MeResponse(
        Long accountId,
        String email,
        Role role,
        Long responsibleId
) {}