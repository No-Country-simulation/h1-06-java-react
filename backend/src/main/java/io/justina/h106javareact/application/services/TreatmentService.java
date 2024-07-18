package io.justina.h106javareact.application.services;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoTreatment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface TreatmentService {

    ReadDtoTreatment create(CreateDtoTreatment createTreatment);
    ReadDtoTreatment findById (String id);
    List<ReadDtoTreatment> findByMedicalProcedureCode (String code);
    List<ReadDtoTreatment> findByMedicalProcedureName (String name);
    List<ReadDtoTreatment> findByPatientId (String patientId);
    List<ReadDtoTreatment> findByDoctorId (String doctorId);
    List<ReadDtoTreatment> findByDate (String date);
    List<ReadDtoTreatment> findByTreatmentStatus (String treatmentStatus);
    List<ReadDtoTreatment> findByPathologyCode (String pathologyCode);
    List<ReadDtoTreatment> findByMedicineCode (String medicineCode);
    ReadDtoTreatment update(UpdateDtoTreatment updateDtoTreatment);
    InputStreamResource downloadMedicalRecordPDF(String id) throws IOException;
}
