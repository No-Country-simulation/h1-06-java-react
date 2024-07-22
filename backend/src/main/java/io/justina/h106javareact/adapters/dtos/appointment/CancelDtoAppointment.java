package io.justina.h106javareact.adapters.dtos.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CancelDtoAppointment(
        @NotNull(message = "La lista de fechas no puede ser nula")
        List<LocalDate> appointmentList
) {
}
