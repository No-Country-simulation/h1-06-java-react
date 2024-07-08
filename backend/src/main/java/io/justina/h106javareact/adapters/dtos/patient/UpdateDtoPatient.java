package io.justina.h106javareact.adapters.dtos.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.justina.h106javareact.domain.entities.enums.Gender;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateDtoPatient(
        @NotNull(message = "El id no puede ser nulo.")
        String id,
        String name,
        String surname,
        String dni,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth,
        Gender gender,
        String healthcareProviderId,
        String socialSecurityNumber
) {
}
