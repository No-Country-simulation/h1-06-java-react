package io.justina.h106javareact.application.services;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoDonation;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoTreatment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface TreatmentService {

    ReadDtoTreatment create(CreateDtoTreatment createTreatment);
    ReadDtoTreatment findById (String id);
    List<ReadDtoTreatment> findByMedicalProcedureCode (String code, String patientId);
    List<ReadDtoTreatment> findByMedicalProcedureName (String name, String patientId);
    List<ReadDtoTreatment> findByPatientId (String patientId);
    List<ReadDtoTreatment> findByDoctorId (String doctorId);
    List<ReadDtoTreatment> findByDate (String date, String patientId);
    List<ReadDtoTreatment> findByTreatmentStatus (String treatmentStatus, String patientId);
    List<ReadDtoTreatment> findByPathologyCode (String pathologyCode, String patientId);
    List<ReadDtoTreatment> findByMedicineCode (String medicineCode, String patientId);
    ReadDtoTreatment update(UpdateDtoTreatment updateDtoTreatment);
    InputStreamResource downloadMedicalRecordPDF(String id) throws IOException;
    ReadDtoTreatment updateDonationData(UpdateDtoDonation updateDtoDonation);
}
