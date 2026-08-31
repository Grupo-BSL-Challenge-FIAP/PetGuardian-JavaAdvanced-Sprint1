package br.com.clyvo.vitalia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_VITALIA_HEALTH_ALERT")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALERT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PET_ID", nullable = false)
    private Pet pet;

    @Column(name = "MEASUREMENT_ID")
    private Long measurementId;

    @Column(name = "ALERT_TYPE", nullable = false, length = 50)
    private String alertType;

    @Column(name = "SEVERITY", nullable = false, length = 20)
    private String severity;

    @Column(name = "MESSAGE", nullable = false, length = 2000)
    private String message;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "RESOLVED_AT")
    private LocalDateTime resolvedAt;
}