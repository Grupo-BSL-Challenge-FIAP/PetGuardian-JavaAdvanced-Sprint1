package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VITALIA_CLINICAL_RECORD")
public class ClinicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLINICAL_RECORD_ID")
    private Long id;

    @Column(name = "PET_ID", nullable = false)
    private Long petId;

    @Column(name = "VETERINARIAN_ID", nullable = false)
    private Long veterinarianId;

    @Column(name = "APPOINTMENT_ID")
    private Long appointmentId;

    @Column(name = "RECORD_DATE", nullable = false)
    private LocalDateTime recordDate;

    @Column(name = "DIAGNOSIS", length = 1000)
    private String diagnosis;

    @Column(name = "OBSERVATIONS", length = 2000)
    private String observations;

    @Column(name = "TREATMENT", length = 2000)
    private String treatment;
}