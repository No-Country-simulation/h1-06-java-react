package io.justina.h106javareact.adapters.dtos.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.justina.h106javareact.domain.entities.enums.Gender;
import io.justina.h106javareact.domain.entities.enums.Specialty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateDtoDoctor(
        @NotNull(message = "El nombre no puede ser nulo.")
        String name,
        @NotNull(message = "El apellido no puede ser nulo.")
        String surname,
        @NotNull(message = "El dni no puede ser nulo.")
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
        @NotNull(message = "La especialidad no puede ser nula.")
        Specialty specialty,
        @NotNull(message = "El número de matrícula no puede ser nulo.")
        String medicalLicense
) {
}