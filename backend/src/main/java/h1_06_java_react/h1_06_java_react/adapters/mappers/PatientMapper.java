package h1_06_java_react.h1_06_java_react.adapters.mappers;

import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.CreateDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.ReadDtoPatient;
import h1_06_java_react.h1_06_java_react.domain.entities.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient createDtoToPatient (CreateDtoPatient createDtoPatient);
    ReadDtoPatient patientToReadDto (Patient patient);

}
