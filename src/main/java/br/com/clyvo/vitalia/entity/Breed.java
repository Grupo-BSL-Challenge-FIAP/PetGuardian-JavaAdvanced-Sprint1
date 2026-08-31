package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VITALIA_BREED")
public class Breed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "BREED_ID")
    private Long id;
    @Column(name = "SPECIES_ID", nullable = false)
    private Long speciesId;
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
}