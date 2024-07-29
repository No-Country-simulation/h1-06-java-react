package io.justina.h106javareact.domain.entities;

import io.justina.h106javareact.domain.entities.enums.DonationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String treatmentId;
    private String donorId;
    private String crossedPatientId;
    private String crossedDonorId;
    private Boolean patientInformedConsent;
    private Boolean donorInformedConsent;
    private Boolean doctorApproval;
    private Boolean patientMedicalClearance;
    private Boolean donorMedicalClearance;
    @Enumerated(EnumType.STRING)
    private DonationType donationType;
}
