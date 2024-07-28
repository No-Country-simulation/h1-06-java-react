package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.medicalProcedure.ReadDtoMedicalProcedure;
import java.io.IOException;
import java.util.List;

public interface MedicalProcedureService {

    List<ReadDtoMedicalProcedure> readAll();
    //Boolean scraping() throws IOException;
}
