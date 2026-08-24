package br.com.clyvo.petguardian.dto.response;

import br.com.clyvo.petguardian.enums.Role;

public record LoginResponse (String token, Long accountId, Role role) {
}
