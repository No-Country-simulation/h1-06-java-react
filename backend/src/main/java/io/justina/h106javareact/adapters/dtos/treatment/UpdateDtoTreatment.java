package io.justina.h106javareact.adapters.dtos.treatment;
import io.justina.h106javareact.adapters.dtos.medicine.ReadDtoMedicine;
import io.justina.h106javareact.domain.entities.Medicine;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;

import java.util.List;

public record UpdateDtoTreatment(
        String id,
        List<String> pathologyCodesList,
        List<String> medicineCodesList,
        String frequency,
        String dosage,
        String administrationDetails,
        TreatmentStatus treatmentStatus
) {
}
