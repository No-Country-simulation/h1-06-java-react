package h1_06_java_react.h1_06_java_react.adapters.dtos.patient;

import h1_06_java_react.h1_06_java_react.domain.entities.enums.BloodType;
import h1_06_java_react.h1_06_java_react.domain.entities.enums.Gender;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateDtoPatient(
        @NotNull(message = "El id no puede ser nulo.")
        String id,
        String name,
        String surname,
        Long dni,
        LocalDate dateOfBirth,
        Gender gender,
        BloodType bloodType
) {
}
