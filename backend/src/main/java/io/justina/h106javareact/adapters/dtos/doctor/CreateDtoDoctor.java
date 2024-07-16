package io.justina.h106javareact.adapters.dtos.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.justina.h106javareact.domain.entities.enums.Gender;
import io.justina.h106javareact.domain.entities.enums.MedicalLicense;
import io.justina.h106javareact.domain.entities.enums.Specialty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateDtoDoctor(
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
        @Past(message = "La fecha de nacimiento debe haber sucedido ya.")
        LocalDate dateOfBirth,
        @NotNull(message = "El género no puede ser nulo.")
        Gender gender,
        @NotNull(message = "El municipio no puede ser nulo.")
        String address,
        @NotNull(message = "La especialidad no puede ser nula.")
        Specialty specialty,
        @NotNull(message = "El tipo de matrícula no puede ser nulo.")
        MedicalLicense licensePlace,
        @NotNull(message = "El número de matrícula no puede ser nulo.")
        String medicalLicense
) {
}
