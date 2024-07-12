package io.justina.h106javareact.adapters.dtos.relative;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.justina.h106javareact.domain.entities.enums.Gender;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateDtoRelative(
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
        String assistedEmail
) {
}
