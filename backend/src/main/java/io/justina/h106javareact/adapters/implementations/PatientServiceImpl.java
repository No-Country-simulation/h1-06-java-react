package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.adapters.mappers.PatientMapper;
import io.justina.h106javareact.adapters.repositories.PatientRepository;
import io.justina.h106javareact.application.services.PatientService;
import io.justina.h106javareact.application.validations.SelfValidation;
import io.justina.h106javareact.domain.entities.Patient;
import io.justina.h106javareact.domain.entities.enums.Role;
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
    public ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoPatient) {
        selfValidation.checkSelfValidation(updateDtoPatient.id());
        Patient patient = patientRepository.findByIdAndActive(updateDtoPatient.id(), true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + updateDtoPatient.id()));

        if (updateDtoPatient.name() != null) {
            patient.setName(updateDtoPatient.name());
        }
        if (updateDtoPatient.surname() != null) {
            patient.setSurname(updateDtoPatient.surname());
        }
        if (updateDtoPatient.dni() != null) {
            patient.setDni(updateDtoPatient.dni());
        }
        if (updateDtoPatient.dateOfBirth() != null) {
            patient.setDateOfBirth(updateDtoPatient.dateOfBirth());
        }
        if (updateDtoPatient.gender() != null) {
            patient.setGender(updateDtoPatient.gender());
        }
        if (updateDtoPatient.bloodType() != null) {
            patient.setBloodType(updateDtoPatient.bloodType());
        }

        this.patientRepository.save(patient);
        return patientMapper.patientToReadDto(patient);
    }

    @Transactional
    @Override
    public Boolean updatePassword(UpdateDtoPassword updateDtoPassword) {
        return null; }


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
