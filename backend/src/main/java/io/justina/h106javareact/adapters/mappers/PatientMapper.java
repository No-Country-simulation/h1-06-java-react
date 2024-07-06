package io.justina.h106javareact.adapters.mappers;

import io.justina.h106javareact.adapters.dtos.user.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.user.ReadDtoPatient;
import io.justina.h106javareact.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    User createDtoToPatient (CreateDtoPatient createDtoPatient);
    ReadDtoPatient patientToReadDto (User user);

}
