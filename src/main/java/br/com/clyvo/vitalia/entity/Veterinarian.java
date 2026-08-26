package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "veterinarians")
public class Veterinarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String cpf;

    private String crmv;

    private String speciality;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}