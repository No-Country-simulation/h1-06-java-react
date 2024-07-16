package io.justina.h106javareact.adapters.dtos.treatment;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreateDtoTreatment(
        @NotNull(message = "El código del tratamiento no puede ser nulo.")
        String medicalProcedureCode,
        @NotNull(message = "Debe haber al menos un código de patología anexo al tratamiento.")
        List<String> pathologyCodesList,
        List<String> medicineCodesList,
        String frequency,
        String dosage,
        String administrationDetails,
        @NotNull(message = "El id del médico no puede ser nulo.")
        String doctorId,
        @NotNull(message = "El id del paciente no puede ser nulo.")
        String patientId,
        @NotNull(message = "El status de tratamiento no puede ser nulo.")
        TreatmentStatus treatmentStatus

) {
}
