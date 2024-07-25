package io.justina.h106javareact.adapters.dtos.patient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.justina.h106javareact.domain.entities.Antibody;
import io.justina.h106javareact.domain.entities.Antigen;
import io.justina.h106javareact.domain.entities.enums.BloodType;
import io.justina.h106javareact.domain.entities.enums.Gender;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public record ReadDtoPatient(
        String id,
        String name,
        String surname,
        String dni,
        String email,
        LocalDate dateOfBirth,
        Gender gender,
        String address,
        BloodType bloodType,
        String healthcareProviderId,
        String socialSecurityNumber,
        Boolean isDonor,
        String relativeDataId,
        Double weight,
        List<Antigen> antigenList,
        List<Antibody> antibodyList
) {
}
