package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VITALIA_SPECIES")
public class Species {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "SPECIES_ID")
    private Long id;
    @Column(name = "NAME", nullable = false, length = 80, unique = true)
    private String name;
}