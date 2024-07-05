package io.justina.h106javareact.adapters.mappers;

import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.domain.entities.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient createDtoToPatient (CreateDtoPatient createDtoPatient);
    ReadDtoPatient patientToReadDto (Patient patient);

}
