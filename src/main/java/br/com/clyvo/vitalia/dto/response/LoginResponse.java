package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.enums.Role;

public record LoginResponse (String token, Long accountId, Role role) {
}
