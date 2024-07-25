package io.justina.h106javareact.adapters.dtos.doctor;

import io.justina.h106javareact.domain.entities.enums.Gender;
import io.justina.h106javareact.domain.entities.enums.MedicalLicense;
import io.justina.h106javareact.domain.entities.enums.Specialty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReadDtoDoctor(
        String id,
        String name,
        String surname,
        String dni,
        String email,
        LocalDate dateOfBirth,
        Gender gender,
        String address,
        Specialty specialty,
        MedicalLicense licensePlace,
        String medicalLicense,
        Boolean morning,
        Boolean afternoon,
        Boolean evening
) {
}
