package br.com.clyvo.vitalia.entity;

import br.com.clyvo.vitalia.enums.*;
import jakarta.persistence.*;
import lombok.*; // Garanta que este import existe
import java.time.LocalDateTime;

@Entity
@Data // <-- ESSA ANOTAÇÃO GERA OS GETTERS (getId, getType, etc)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AlertType type;

    private String message;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}