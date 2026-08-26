package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "responsibles")
public class Responsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String cpf;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String address;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}