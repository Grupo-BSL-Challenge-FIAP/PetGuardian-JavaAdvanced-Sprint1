package br.com.clyvo.petguardian.dto.request;

import br.com.clyvo.petguardian.enums.Role;

public record RegisterRequest (String email, String password, Role role){
    
}
