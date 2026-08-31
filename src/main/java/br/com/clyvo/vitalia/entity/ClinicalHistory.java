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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PET_ID", nullable = false)
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VETERINARIAN_ID", nullable = false)
    private AppUser veterinarian;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPOINTMENT_ID")
    private Appointment appointment;

    @Column(name = "RECORD_DATE", nullable = false)
    private LocalDateTime recordDate;

    @Column(name = "DIAGNOSIS", length = 1000)
    private String diagnosis;

    @Column(name = "OBSERVATIONS", length = 2000)
    private String observations;

    @Column(name = "TREATMENT", length = 2000)
    private String treatment;
}