package io.justina.h106javareact.adapters.implementations;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.UpdateDtoTreatment;
import io.justina.h106javareact.adapters.mappers.TreatmentMapper;
import io.justina.h106javareact.adapters.repositories.*;
import io.justina.h106javareact.application.services.TreatmentService;
import io.justina.h106javareact.domain.entities.*;
import io.justina.h106javareact.domain.entities.enums.TreatmentStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    private final PatientDataRepository patientDataRepository;

    @Transactional
    @Override
    public ReadDtoTreatment create(CreateDtoTreatment createDtoTreatment) {
        for (String pathologyCode : createDtoTreatment.pathologyCodesList()) {
            if (pathologyCode.toString().equals("Z524")){
                registerKidneyDonation(createDtoTreatment);
                break;
            }
        }

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
        for (String medicineCode : createDtoTreatment.medicineCodesList()) {
            var medicine = medicineRepository.findByCode(medicineCode)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "No se puede encontrar el medicamento con el código " + medicineCode));
            medicineEntities.add(medicine);
        }
        entity.setMedicineList(medicineEntities);

        List<Pathology> pathologiesEntities = new ArrayList<>();
        for (String pathologyCode : createDtoTreatment.pathologyCodesList()) {
            var pathology = pathologyRepository.findByCode(pathologyCode)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "No se puede encontrar la patología o trastorno con el código " + pathologyCode));
            pathologiesEntities.add(pathology);
        }
        entity.setPathologyList(pathologiesEntities);

        var savedTreatment = treatmentRepository.save(entity);
        return treatmentMapper.entityToReadTreatment(savedTreatment);
    }

    @Override
    public ReadDtoTreatment findById(String id) {
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

    @Override
    public List<ReadDtoTreatment> findByPatientId(String patientId) {
        List<Treatment> foundTreatments = treatmentRepository.findByPatientId(patientId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar ningún tratamiento para el paciente " + patientId));
        return treatmentMapper.entityListToReadTreatmentList(foundTreatments);
    }

    @Override
    public List<ReadDtoTreatment> findByDoctorId(String doctorId) {
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

    @Transactional
    @Override
    public ReadDtoTreatment update(UpdateDtoTreatment updateDtoTreatment) {
        Treatment treatment = treatmentRepository.findById(updateDtoTreatment.id())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se puede encontrar el tratamiento con el id " + updateDtoTreatment.id()));

        if (updateDtoTreatment.pathologyCodesList() != null) {
            List<Pathology> pathologyList = new ArrayList<>();
            for (String code : updateDtoTreatment.pathologyCodesList()) {
                Pathology entity = pathologyRepository.findByCode(code)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se puede encontrar la patología con el código " + code));
                pathologyList.add(entity);
            }
            treatment.setPathologyList(pathologyList);
        }
        if (updateDtoTreatment.medicineCodesList() != null) {
            List<Medicine> medicineList = new ArrayList<>();
            for (String code : updateDtoTreatment.medicineCodesList()) {
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

    @Transactional
    @Override
    public InputStreamResource downloadMedicalRecordPDF
            (@PathVariable String id) throws IOException {
        User patient = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se halló paciente con id " + id));
        List<Treatment> medicalRecord = treatmentRepository.findByPatientId(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No hay tratamientos que mostrar para el paciente con id " + id));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("HISTORIA CLÍNICA"));
        document.add(new Paragraph("Paciente: " + patient.getName() +  " " + patient.getSurname() + "."));
        document.add(new Paragraph("_________________________"));
        document.add(new Paragraph("_________________________"));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        for (var treatment : medicalRecord) {
            document.add(new Paragraph("Fecha de consulta: " + treatment.getDate().toLocalDate().toString() + "."));
            document.add(new Paragraph("Práctica médica: " + treatment.getMedicalProcedureCode() + "."));

            for (var pathologies : treatment.getPathologyList()) {
                var pathologyCode = pathologies.getCode();
                document.add(new Paragraph("Patología/s: " + pathologyCode + "."));
            }

            for (var medicines : treatment.getMedicineList()) {
                var medicineName = medicines.getName();
                document.add(new Paragraph("Medicamentos asociados: " + medicineName + "."));
            }

            document.add(new Paragraph("Estado del tratamiento: " + treatment.getTreatmentStatus() + "."));

            var doctor = userRepository.findById(treatment.getDoctorId());
            document.add(new Paragraph("Profesional a cargo: " + doctor.get().getSurname() + ", " + doctor.get().getName() +"."));
            document.add(new Paragraph("_________________________"));
            document.add(new Paragraph(""));
        }

        document.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

        return new InputStreamResource(bis);
    }

    public void registerKidneyDonation(CreateDtoTreatment createDtoTreatment){
        User donor = userRepository.findById(createDtoTreatment.donorId())
                .orElseThrow(() -> new EntityNotFoundException("El donante no debe ser nulo"));
        PatientData donorData = patientDataRepository.findById(donor.getPatientDataId())
                .orElseThrow(() -> new EntityNotFoundException("El donante no debe ser nulo"));
        User recipient = userRepository.findById(createDtoTreatment.donorId())
                .orElseThrow(() -> new EntityNotFoundException("El donante no debe ser nulo"));
        PatientData recipientData = patientDataRepository.findById(recipient.getPatientDataId())
                .orElseThrow(() -> new EntityNotFoundException("El paciente no debe ser nulo"));

        boolean ageDifference = checkAgeDifference(donor, recipient);
        boolean weightDifference = checkWeightDifference(donorData, recipientData);
        boolean antigenDifference = checkAntigenDifference(donorData, recipientData);
        boolean antigenAntibodyDifference = checkAntigenAntibody(donorData, recipientData);
        boolean bloodTypeCompatibility = checkBloodTypes(donorData, recipientData);

        if (ageDifference && weightDifference && antigenDifference
                && antigenAntibodyDifference && bloodTypeCompatibility){
            //TODO. "IF TRUE, son compatibles donante y paciente."
            }

    }

    public boolean checkAgeDifference(User donor, User recipient){
        var donorAge = donor.getDateOfBirth();
        var recipientAge = recipient.getDateOfBirth();
        var ageDifference = ChronoUnit.YEARS.between(donorAge, recipientAge);
        if (ageDifference > 20) { return false; }
        return true;
    }

    public boolean checkWeightDifference(PatientData donor, PatientData recipient){
        var donorWeight = donor.getWeight();
        var recipientWeight = recipient.getWeight();
        var weightDifference = Math.abs(donorWeight - recipientWeight);
        var thresholdPercentage = (weightDifference * 100) / recipientWeight;
        if (thresholdPercentage >= 30.0) { return false; }
        return true;
    }

    public boolean checkAntigenDifference(PatientData donor, PatientData recipient) {
        var donorAntigens = donor.getAntigenList();
        var recipientAntigens = recipient.getAntigenList();
        int antigenCount = 0;
        for (Antigen antigenD : donorAntigens){
            for (Antigen antigenR : recipientAntigens){
                if (antigenD.getType().equals(antigenR.getType())){ antigenCount++; break; }
            }
        }
        if (antigenCount <= 3) { return false; }
        return true;
    }

    public boolean checkAntigenAntibody(PatientData donor, PatientData recipient) {
        var donorAntigens = donor.getAntigenList();
        var recipientAntibodies = recipient.getAntibodyList();
        for (Antigen antigenD : donorAntigens){
            for (Antibody antibodyR : recipientAntibodies){
                if (antigenD.getType().equals(antibodyR.getType())){ return false; }
            }
        }
        return true;
    }

    public boolean checkBloodTypes(PatientData donor, PatientData recipient){
        var donorBlood = donor.getBloodType().toString();
        var recipientBlood = recipient.getBloodType().toString();

        //Casos donde la pareja donante es compatible:
        if (recipientBlood.equals("AB_POSITIVO")) {return false; } //si el receptor es ABPos, receptor universal
        if (recipientBlood.equals("AB_NEGATIVO") && donorBlood.contains("NEGATIVO")) {return false; } //si el receptor es ABNeg y el donante es Neg.
        if (recipientBlood.equals("A_POSITIVO")
                && (donorBlood.contains("A_") || (donorBlood.contains("O_")))) { return false;} //si el receptor es Apos y el donante es A o O.
        if (recipientBlood.equals("B_POSITIVO")
                && (donorBlood.contains("B_") || (donorBlood.contains("O_")))) { return false;} //si el receptor es Bpos y el donante es B o O.
        if (recipientBlood.equals("A_NEGATIVO")
                && (donorBlood.contains("A_NEGATIVO") || (donorBlood.contains("O_NEGATIVO")))) { return false;} //si el receptor es Aneg y el donante es Aneg o Oneg.
        if (recipientBlood.equals("B_NEGATIVO")
                && (donorBlood.equals("B_NEGATIVO") || (donorBlood.equals("O_NEGATIVO")))) { return false;}  //si el receptor es Bneg y el donante es Bneg o Oneg.
        if (recipientBlood.equals("O_POSITIVO") && donorBlood.contains("O_")) {return false; } //si el receptor es Opos y el donante es O.
        if (recipientBlood.equals("O_NEGATIVO") && donorBlood.equals("O_NEGATIVO")) {return false; } //si el receptor es ONeg y el donante también es Oneg.
        return true;
    }

}


/*Resumen de Compatibilidad
A positivo: Recibe de A positivo, A negativo, O positivo, O negativo; Dona a A positivo, AB positivo.
A negativo: Recibe de A negativo, O negativo; Dona a A positivo, A negativo, AB positivo, AB negativo.
B positivo: Recibe de B positivo, B negativo, O positivo, O negativo; Dona a B positivo, AB positivo.
B negativo: Recibe de B negativo, O negativo; Dona a B positivo, B negativo, AB positivo, AB negativo.
AB positivo: Recibe de todos los tipos de sangre; Dona solo a AB positivo.
AB negativo: Recibe de A negativo, B negativo, AB negativo, O negativo; Dona a AB positivo, AB negativo.
O positivo: Recibe de O positivo, O negativo; Dona a todos los tipos de sangre positivos.
O negativo: Recibe solo de O negativo; Dona a todos los tipos de sangre.
* */