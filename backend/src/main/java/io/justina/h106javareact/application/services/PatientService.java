package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.medicalInformation.AddDtoMedicalInformation;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import org.apache.coyote.BadRequestException;

public interface PatientService {
    ReadDtoPatient createPatient(CreateDtoPatient createDtoPatient) throws Exception;
    ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoPatient);
    ReadDtoPatient updateMedicalInformation(AddDtoMedicalInformation medicalInformation) throws BadRequestException;
}
