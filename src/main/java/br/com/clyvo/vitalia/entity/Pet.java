package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VITALIA_PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_ID")
    private Long id;

    @Column(name = "OWNER_USER_ID", nullable = false)
    private Long ownerUserId;

    @Column(name = "BREED_ID")
    private Long breedId;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "SEX", nullable = false, length = 20)
    private String sex;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "WEIGHT_KG", precision = 6, scale = 2)
    private BigDecimal weightKg;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
}