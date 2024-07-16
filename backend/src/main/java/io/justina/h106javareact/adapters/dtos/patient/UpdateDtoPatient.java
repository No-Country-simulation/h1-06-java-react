package io.justina.h106javareact.adapters.dtos.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.justina.h106javareact.domain.entities.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UpdateDtoPatient(
        @NotNull(message = "El id no puede ser nulo.")
        String id,
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre solo debe contener letras mayúsculas, minúsculas y espacios")
        String name,
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido solo debe contener letras mayúsculas, minúsculas y espacios")
        String surname,
        @Pattern(regexp = "^[A-Z0-9]+$", message = "El DNI solo debe contener letras mayúsculas y números")
        String dni,
        String email,
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Past(message = "La fecha de nacimiento debe haber sucedido ya.")
        LocalDate dateOfBirth,
        Gender gender,
        String address,
        String healthcareProviderId,
        String socialSecurityNumber
) {
}
