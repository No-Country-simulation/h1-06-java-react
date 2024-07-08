package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;

public interface DoctorService {
    ReadDtoDoctor createDoctor(CreateDtoDoctor createDtoDoctor);
    ReadDtoDoctor readDoctorById(String id, Boolean active);
    ReadDtoDoctor readDoctorByEmail(String email, Boolean active);
    ReadDtoDoctor updateDoctor(UpdateDtoDoctor updateDtoDoctor);
    Boolean toggleDoctor(String id);
}
