package h1_06_java_react.h1_06_java_react.application.services;

import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.CreateDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.ReadDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.UpdateDtoPassword;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.UpdateDtoPatient;

public interface PatientService {
    ReadDtoPatient createPatient(CreateDtoPatient createDtoUser);
    ReadDtoPatient readPatientById(String id, Boolean active);
    ReadDtoPatient readPatientByEmail(String email, Boolean active);
    ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoUser);
    Boolean updatePassword (UpdateDtoPassword updateDtoPassword);
    Boolean togglePatient(String id);
}
