package io.justina.h106javareact.adapters.dtos.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateDtoAppointment(
        @NotNull(message = "El id del turno no puede ser nulo.")
        String id,
        @NotNull(message = "La fecha del turno no debe ser nula.")
        @Future(message = "La fecha del turno debe ser a futuro.")
        LocalDateTime date
) {
}
