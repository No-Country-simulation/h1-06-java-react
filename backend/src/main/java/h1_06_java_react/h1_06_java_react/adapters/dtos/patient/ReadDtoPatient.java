package h1_06_java_react.h1_06_java_react.adapters.dtos.patient;

import h1_06_java_react.h1_06_java_react.domain.entities.enums.BloodType;
import h1_06_java_react.h1_06_java_react.domain.entities.enums.Gender;

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
