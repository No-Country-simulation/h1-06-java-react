package io.justina.h106javareact.adapters.dtos.treatment;

public record UpdateDtoDonation(
        String treatmentId,
        Boolean patientInformedConsent,
        Boolean donorInformedConsent,
        Boolean doctorApproval,
        Boolean patientMedicalClearance,
        Boolean donorMedicalClearance
) {
}
