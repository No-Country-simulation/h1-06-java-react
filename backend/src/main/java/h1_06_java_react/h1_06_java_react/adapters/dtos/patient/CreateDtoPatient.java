package h1_06_java_react.h1_06_java_react.adapters.dtos.patient;

import h1_06_java_react.h1_06_java_react.domain.entities.enums.BloodType;
import h1_06_java_react.h1_06_java_react.domain.entities.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateDtoPatient(
        @NotNull(message = "El nombre no puede ser nulo.")
        String name,
        @NotNull(message = "El apellido no puede ser nulo.")
        String surname,
        @NotNull(message = "El dni no puede ser nulo.")
        Long dni,
        @Email
        @NotNull(message = "El email no puede ser nulo.")
        String email,
        @NotNull(message = "La contraseña no puede ser nula.")
        @Size(min = 7, message = "La contraseña debe tener al menos 8 carateres.")
        String password,
        //@NotNull(message = "La fecha de nacimiento no puede ser nula.")
        LocalDate dateOfBirth,
        //@NotNull(message = "El género no puede ser nulo.")
        Gender gender,
        //@NotNull(message = "El tipo de sangre no puede ser nulo.")
        BloodType bloodType
) {
}
