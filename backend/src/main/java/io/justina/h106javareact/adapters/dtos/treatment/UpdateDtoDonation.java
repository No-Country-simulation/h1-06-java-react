package io.justina.h106javareact.adapters.dtos.treatment;

import jakarta.validation.constraints.NotNull;

public record UpdateDtoDonation(
        @NotNull
        String treatmentId,
        Boolean patientInformedConsent,
        Boolean donorInformedConsent,
        Boolean doctorApproval,
        Boolean patientMedicalClearance,
        Boolean donorMedicalClearance
) {
}
