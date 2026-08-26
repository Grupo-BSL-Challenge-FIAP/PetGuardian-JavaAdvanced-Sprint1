package br.com.clyvo.vitalia.entity;

import br.com.clyvo.vitalia.enums.CurrentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String breed;
    private String gender;
    private LocalDate birthDate;
    private Double weight;

    @Enumerated(EnumType.STRING)
    private CurrentStatus currentStatus;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private Responsible responsible;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private Veterinarian veterinarian;
}