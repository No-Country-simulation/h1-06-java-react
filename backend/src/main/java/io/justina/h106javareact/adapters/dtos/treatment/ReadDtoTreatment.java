package io.justina.h106javareact.adapters.dtos.treatment;
import io.justina.h106javareact.adapters.dtos.medicine.ReadDtoMedicine;
import io.justina.h106javareact.adapters.dtos.pathology.ReadDtoPathology;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import java.util.List;

public record ReadDtoTreatment(
        String id,
        String medicalProcedureCode,
        String medicalProcedureName,
        List<ReadDtoPathology> pathologyList,
        List<ReadDtoMedicine> medicineList,
        String frequency,
        String dosage,
        String administrationDetails,
        String doctorId,
        String patientId,
        TreatmentStatus treatmentStatus
) {
}
