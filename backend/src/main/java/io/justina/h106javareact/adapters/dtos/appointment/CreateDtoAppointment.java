package io.justina.h106javareact.adapters.dtos.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateDtoAppointment(
        @NotNull(message = "El id de paciente no puede ser nulo.")
        String patientId,
        String relativeId,
        @NotNull(message = "El id de doctor no puede ser nulo.")
        String doctorId,
        @NotNull(message = "La fecha no puede ser nula.")
        @Future(message = "La fecha debe ser a futuro.")
        LocalDateTime date
) {
}
