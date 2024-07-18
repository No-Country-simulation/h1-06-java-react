package io.justina.h106javareact.adapters.mappers;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.domain.entities.DoctorData;
import io.justina.h106javareact.domain.entities.PatientData;
import io.justina.h106javareact.domain.entities.RelativeData;
import io.justina.h106javareact.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User createPatientDtoToUser (CreateDtoPatient createDtoPatient);
    PatientData createPatientDtoToData (CreateDtoPatient createDtoPatient);

    User createDoctorDtoToUser (CreateDtoDoctor createDtoDoctor);
    DoctorData createDoctorDtoToData (CreateDtoDoctor createDtoDoctor);

    User createRelativeDtoToUser (CreateDtoRelative createDtoRelative);
    RelativeData createRelativeDtoToData (CreateDtoRelative createDtoRelative);

    @Mapping(source = "user.id", target = "id")
    ReadDtoPatient entityToReadDtoPatient (User user, PatientData patientData);

    @Mapping(source = "user.id", target = "id")
    ReadDtoDoctor entityToReadDtoDoctor (User user, DoctorData doctorData);

    @Mapping(source = "user.id", target = "id")
    ReadDtoRelative entityToReadDtoRelative (User user, RelativeData relativeData);
}
