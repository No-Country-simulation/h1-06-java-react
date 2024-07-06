package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.user.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.user.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.user.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.user.UpdateDtoPatient;

public interface UserService {
    ReadDtoPatient createPatient(CreateDtoPatient createDtoUser);
    ReadDtoPatient readPatientById(String id, Boolean active);
    ReadDtoPatient readPatientByEmail(String email, Boolean active);
    ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoUser);
    Boolean updatePassword (UpdateDtoPassword updateDtoPassword);
    Boolean togglePatient(String id);
}
