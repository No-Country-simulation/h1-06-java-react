package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;

public interface PatientService {
    ReadDtoPatient createPatient(CreateDtoPatient createDtoPatient);
    ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoPatient);
}