package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;

import java.util.List;

public interface DoctorService {
    ReadDtoDoctor createDoctor(CreateDtoDoctor createDtoDoctor);
   ReadDtoDoctor updateDoctor(UpdateDtoDoctor updateDtoDoctor);
   List<ReadDtoDoctor> readBySpecialty(String specialty);

}
