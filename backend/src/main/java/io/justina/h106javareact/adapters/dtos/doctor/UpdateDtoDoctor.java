package io.justina.h106javareact.adapters.dtos.doctor;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.justina.h106javareact.domain.entities.enums.Gender;
import io.justina.h106javareact.domain.entities.enums.MedicalLicense;
import io.justina.h106javareact.domain.entities.enums.Specialty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateDtoDoctor(
        @NotNull(message = "El id no puede ser nulo.")
        String id,
        String name,
        String surname,
        String email,
        String dni,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth,
        Gender gender,
        String address,
        Specialty specialty,
        MedicalLicense licensePlace,
        String medicalLicense
) {
}
