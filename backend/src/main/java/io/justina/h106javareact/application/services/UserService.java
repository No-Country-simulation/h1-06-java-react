package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.login.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;

public interface UserService {
    ReadDtoPatient createPatient(CreateDtoPatient createDtoUser);
    ReadDtoDoctor createDoctor(CreateDtoDoctor createDtoDoctor);
    ReadDtoRelative createRelative(CreateDtoRelative createDtoRelative);
    ReadDtoPatient readPatientById(String id, Boolean active);
    ReadDtoPatient readPatientByEmail(String email, Boolean active);
    ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoUser);
    Boolean updatePassword (UpdateDtoPassword updateDtoPassword);
    Boolean togglePatient(String id);
}
