package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;

public interface PatientService {
    ReadDtoPatient createPatient(CreateDtoPatient createDtoUser);
    ReadDtoPatient readPatientById(String id, Boolean active);
    ReadDtoPatient readPatientByEmail(String email, Boolean active);
    ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoUser);
    Boolean updatePassword (UpdateDtoPassword updateDtoPassword);
    Boolean togglePatient(String id);
}
