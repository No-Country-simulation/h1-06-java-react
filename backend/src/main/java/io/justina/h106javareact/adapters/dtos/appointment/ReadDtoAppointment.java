package io.justina.h106javareact.adapters.dtos.appointment;

import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;

import java.time.LocalDateTime;

public record ReadDtoAppointment(
        String id,
        ReadDtoDoctor doctorId,
        ReadDtoRelative relativeId,
        ReadDtoPatient patientId,
        LocalDateTime date,
        String observations
) {
}
