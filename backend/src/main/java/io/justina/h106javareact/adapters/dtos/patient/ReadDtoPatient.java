package io.justina.h106javareact.adapters.dtos.patient;

import io.justina.h106javareact.domain.entities.enums.BloodType;
import io.justina.h106javareact.domain.entities.enums.Gender;

import java.time.LocalDate;

public record ReadDtoPatient(
        String id,
        String entityId,
        String healthcareProviderId,
        String doctorStaffId,
        String documentTypeId,
        String name,
        String surname,
        Long dni,
        String email,
        LocalDate dateOfBirth,
        Gender gender,
        BloodType bloodType
) {
}
