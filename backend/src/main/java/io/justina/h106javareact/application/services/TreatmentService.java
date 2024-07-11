package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;

public interface TreatmentService {

    ReadDtoTreatment create(CreateDtoTreatment createTreatment);
}
