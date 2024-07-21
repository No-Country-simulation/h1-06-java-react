package io.justina.h106javareact.adapters.implementations;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoTreatment;
import io.justina.h106javareact.adapters.mappers.TreatmentMapper;
import io.justina.h106javareact.adapters.repositories.*;
import io.justina.h106javareact.application.services.TreatmentService;
import io.justina.h106javareact.application.validations.Validations;
import io.justina.h106javareact.domain.entities.*;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final Validations validations;

    @Transactional
    @Override
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

    public ReadDtoTreatment findById (String id){
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento con el id " + id));
        return treatmentMapper.entityToReadTreatment(treatment);
    }

    @Override
    public List<ReadDtoTreatment> findByMedicalProcedureCode(String code, String patientId) {
        List<Treatment> foundTreatments = treatmentRepository
                .findByMedicalProcedureCodeAndPatientId(code, patientId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento con el código " + code));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    @Override
    public List<ReadDtoTreatment> findByMedicalProcedureName(String name, String patientId) {
        List<Treatment> foundTreatments = treatmentRepository
                .findByMedicalProcedureNameAndPatientId(name, patientId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento con el nombre " + name));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    public List<ReadDtoTreatment> findByPatientId (String patientId) {
        List<Treatment> foundTreatments = treatmentRepository.findByPatientId(patientId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento para el paciente " + patientId));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    public List<ReadDtoTreatment> findByDoctorId (String doctorId) {
        List<Treatment> foundTreatments = treatmentRepository.findByDoctorId(doctorId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento recetado por el profesional " + doctorId));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    @Override
    public List<ReadDtoTreatment> findByDate(String date, String patientId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime entity = LocalDateTime.parse(date, formatter);

        List<Treatment> foundTreatments = treatmentRepository.findByDateAndPatientId(entity, patientId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento con fecha de " + date));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    @Override
    public List<ReadDtoTreatment> findByTreatmentStatus(String treatmentStatus, String patientId) {
        var entity = TreatmentStatus.valueOf(treatmentStatus);
        List<Treatment> foundTreatments = treatmentRepository.findByTreatmentStatus(entity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento con el estado de " + treatmentStatus));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    @Override
    public List<ReadDtoTreatment> findByPathologyCode(String pathologyCode, String patientId) {
        Pathology entity = pathologyRepository.findByCode(pathologyCode)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar la patología con el código " + pathologyCode));

        List<Treatment> foundTreatments = treatmentRepository.findByPathologyAndPatientId(entity, patientId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento para la patología " + pathologyCode));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    @Override
    public List<ReadDtoTreatment> findByMedicineCode(String medicineCode, String patientId) {
        Medicine entity = medicineRepository.findByCode(medicineCode)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar el medicamento con el código " + medicineCode));

        List<Treatment> foundTreatments = treatmentRepository.findByMedicineAndPatientId(entity, patientId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento que tenga el medicamento " + medicineCode + " recetado."));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    public ReadDtoTreatment update(UpdateDtoTreatment updateDtoTreatment){
        Treatment treatment = treatmentRepository.findById(updateDtoTreatment.id())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar el tratamiento con el id " + updateDtoTreatment.id()));

        if (updateDtoTreatment.pathologyCodesList() != null) {
            List<Pathology> pathologyList = new ArrayList<>();
            for (String code : updateDtoTreatment.pathologyCodesList()){
                Pathology entity = pathologyRepository.findByCode(code)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se puede encontrar la patología con el código " + code));
                pathologyList.add(entity);
            }
            treatment.setPathologyList(pathologyList);
        }
        if (updateDtoTreatment.medicineCodesList() != null) {
            List<Medicine> medicineList = new ArrayList<>();
            for (String code : updateDtoTreatment.medicineCodesList()){
                Medicine entity = medicineRepository.findByCode(code)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se puede encontrar el medicamento con el código " + code));
                medicineList.add(entity);
            }
            treatment.setMedicineList(medicineList);
        }
        if (updateDtoTreatment.frequency() != null) {
            treatment.setFrequency(updateDtoTreatment.frequency());
        }
        if (updateDtoTreatment.dosage() != null) {
            treatment.setDosage(updateDtoTreatment.dosage());
        }
        if (updateDtoTreatment.administrationDetails() != null) {
            treatment.setAdministrationDetails(updateDtoTreatment.administrationDetails());
        }
        if (updateDtoTreatment.treatmentStatus() != null) {
            treatment.setTreatmentStatus(updateDtoTreatment.treatmentStatus());
        }

        this.treatmentRepository.save(treatment);
        return treatmentMapper.entityToReadTreatment(treatment);
    }

}
