package h1_06_java_react.h1_06_java_react.adapters.implementations;

import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.CreateDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.ReadDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.UpdateDtoPassword;
import h1_06_java_react.h1_06_java_react.adapters.dtos.patient.UpdateDtoPatient;
import h1_06_java_react.h1_06_java_react.adapters.mappers.PatientMapper;
import h1_06_java_react.h1_06_java_react.adapters.repositories.PatientRepository;
import h1_06_java_react.h1_06_java_react.application.services.PatientService;
import h1_06_java_react.h1_06_java_react.application.validations.SelfValidation;
import h1_06_java_react.h1_06_java_react.domain.entities.Patient;
import h1_06_java_react.h1_06_java_react.domain.entities.enums.Role;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;
    public final SelfValidation selfValidation;

    @Transactional
    @Override
    public ReadDtoPatient createPatient(CreateDtoPatient createDtoPatient) {
        var userAlreadyExists = patientRepository.findByEmail(createDtoPatient.email());
        if(userAlreadyExists.isPresent()){ throw new EntityExistsException("¡Este email ya está en uso!"); }

        Patient patient = this.patientMapper.createDtoToPatient(createDtoPatient);
        patient.setPassword(passwordEncoder.encode(createDtoPatient.password()));

        patient.setActive(Boolean.TRUE);
        patient.setRole(Role.PATIENT);
        var patientAdded = patientRepository.save(patient);
        return patientMapper.patientToReadDto(patientAdded);
    }

    @Override
    public ReadDtoPatient readPatientById(String id, Boolean active) {
        Patient patient = patientRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + id));

        return patientMapper.patientToReadDto(patient);
    }

    @Override
    public ReadDtoPatient readPatientByEmail(String email, Boolean active) {
        Patient patient = patientRepository.findByEmailAndActive(email, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el email " + email));

        return patientMapper.patientToReadDto(patient);
    }

    @Transactional
    @Override
    public ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoUser) {
        return null;
    }

    @Transactional
    @Override
    public Boolean updatePassword(UpdateDtoPassword updateDtoPassword) {
        return null;
    }

    @Transactional
    @Override
    public Boolean togglePatient(String id) {
        selfValidation.checkSelfValidation(id);
        Patient patientEntity = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + id));
        patientEntity.setActive(!patientEntity.getActive());
        patientRepository.save(patientEntity);
        return true;
    }
}
