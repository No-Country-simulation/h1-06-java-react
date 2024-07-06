package io.justina.h106javareact.domain.entities;

import io.justina.h106javareact.domain.entities.enums.BloodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;
    private String healthcareProviderId;
    private String socialSecurityNumber;
    private boolean isDonor;
}
