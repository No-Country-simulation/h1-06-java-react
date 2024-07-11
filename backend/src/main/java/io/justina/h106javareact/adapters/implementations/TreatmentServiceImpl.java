package io.justina.h106javareact.adapters.implementations;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.mappers.TreatmentMapper;
import io.justina.h106javareact.adapters.repositories.*;
import io.justina.h106javareact.application.services.TreatmentService;
import io.justina.h106javareact.domain.entities.MedicalProcedure;
import io.justina.h106javareact.domain.entities.Medicine;
import io.justina.h106javareact.domain.entities.Pathology;
import io.justina.h106javareact.domain.entities.PatientData;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final MedicineRepository medicineRepository;
    private final PathologyRepository pathologyRepository;
    private final MedicalProcedureRepository medicalProcedureRepository;
    private final UserRepository userRepository;
    private final TreatmentMapper treatmentMapper;

    public ReadDtoTreatment create(CreateDtoTreatment createDtoTreatment){
        var entity = treatmentMapper.createTreatmentToEntity(createDtoTreatment);

        var patient = userRepository.findById(createDtoTreatment.patientId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar el paciente con el id " + createDtoTreatment.patientId()));
        entity.setPatient(patient);

        String code = createDtoTreatment.medicalProcedureCode();
        var medicalProcedure = medicalProcedureRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar el tratamiento con el código " + code));
        entity.setMedicalProcedureCode(code);
        entity.setMedicalProcedureName(medicalProcedure.getName());

        List<Medicine> medicineEntities = new ArrayList<>();
        for (String medicineCode : createDtoTreatment.medicineCodesList()){
            var medicine = medicineRepository.findByCode(medicineCode)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "No se puede encontrar el medicamento con el código " + medicineCode));
            medicineEntities.add(medicine);
        }
        entity.setMedicineList(medicineEntities);

        List<Pathology> pathologiesEntities = new ArrayList<>();
        for (String pathologyCode : createDtoTreatment.pathologyCodesList()){
            var pathology = pathologyRepository.findByCode(pathologyCode)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "No se puede encontrar la patología o trastorno con el código " + pathologyCode));
            pathologiesEntities.add(pathology);
        }
        entity.setPathologyList(pathologiesEntities);

        var savedTreatment = treatmentRepository.save(entity);
        return treatmentMapper.entityToReadTreatment(savedTreatment);
    }
}
