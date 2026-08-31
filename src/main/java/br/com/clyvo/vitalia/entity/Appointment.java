package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VITALIA_APPOINTMENT")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Long id;

    @Column(name = "PET_ID", nullable = false)
    private Long petId;

    @Column(name = "VETERINARIAN_ID", nullable = false)
    private Long veterinarianId;

    @Column(name = "APPOINTMENT_DATE", nullable = false)
    private LocalDateTime appointmentDate;

    @Column(name = "STATUS", nullable = false, length = 30)
    private String status;

    @Column(name = "NOTES", length = 1000)
    private String notes;
}