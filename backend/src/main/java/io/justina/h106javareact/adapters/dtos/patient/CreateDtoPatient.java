package io.justina.h106javareact.adapters.dtos.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.justina.h106javareact.domain.entities.enums.BloodType;
import io.justina.h106javareact.domain.entities.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateDtoPatient(
        @NotNull(message = "El nombre no puede ser nulo.")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre solo debe contener letras mayúsculas, minúsculas y espacios")
        String name,
        @NotNull(message = "El apellido no puede ser nulo.")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido solo debe contener letras mayúsculas, minúsculas y espacios")
        String surname,
        @NotNull(message = "El dni no puede ser nulo.")
        @Pattern(regexp = "^[A-Z0-9]+$", message = "El DNI solo debe contener letras mayúsculas y números")
        String dni,
        @Email
        @NotNull(message = "El email no puede ser nulo.")
        String email,
        @NotNull(message = "La contraseña no puede ser nula.")
        @Size(min = 7, message = "La contraseña debe tener al menos 8 carateres.")
        String password,
        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull(message = "La fecha de nacimiento no puede ser nula.")
        LocalDate dateOfBirth,
        @NotNull(message = "El género no puede ser nulo.")
        Gender gender,
        BloodType bloodType,
        @NotNull(message = "El municipio no puede ser nulo.")
        String address,
        String healthcareProviderId,
        String socialSecurityNumber,
        @NotNull(message = "El status de donante no puede ser nulo.")
        Boolean isDonor

) {
}
