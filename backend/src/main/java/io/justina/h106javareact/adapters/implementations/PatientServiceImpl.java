package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.adapters.mappers.UserMapper;
import io.justina.h106javareact.adapters.repositories.PatientDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.services.PatientService;
import io.justina.h106javareact.application.validations.SelfValidation;
import io.justina.h106javareact.domain.entities.PatientData;
import io.justina.h106javareact.domain.entities.RelativeData;
import io.justina.h106javareact.domain.entities.User;
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
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatientDataRepository patientDataRepository;
    private final PasswordEncoder passwordEncoder;
    public final SelfValidation selfValidation;

    @Transactional
    @Override
    public ReadDtoPatient createPatient(CreateDtoPatient createDtoPatient) {
        var userAlreadyExists = userRepository.findByEmail(createDtoPatient.email());
        if(userAlreadyExists.isPresent()){ throw new EntityExistsException("¡Este email ya está en uso!"); }

        PatientData patientData = this.userMapper.createPatientDtoToData(createDtoPatient);
        var patientDataAdded = patientDataRepository.save(patientData);

        User user = this.userMapper.createPatientDtoToUser(createDtoPatient);
        user.setPassword(passwordEncoder.encode(createDtoPatient.password()));

        user.setActive(Boolean.TRUE);
        user.setRole(Role.PACIENTE);
        user.setPatientDataId(patientDataAdded.getId());
        var patientAdded = userRepository.save(user);
        return userMapper.entityToReadDtoPatient(patientAdded, patientDataAdded);
    }

    @Transactional
    @Override
    public ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoPatient) {
        selfValidation.checkSelfValidation(updateDtoPatient.id());
        User user = userRepository.findByIdAndActive(updateDtoPatient.id(), true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + updateDtoPatient.id()));
        var patientData = patientDataRepository.findById(user.getPatientDataId())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + updateDtoPatient.id()));

        if (updateDtoPatient.name() != null) {
            user.setName(updateDtoPatient.name());
        }
        if (updateDtoPatient.surname() != null) {
            user.setSurname(updateDtoPatient.surname());
        }
        if (updateDtoPatient.dni() != null) {
            user.setDni(updateDtoPatient.dni());
        }
        if (updateDtoPatient.dateOfBirth() != null) {
            user.setDateOfBirth(updateDtoPatient.dateOfBirth());
        }
        if (updateDtoPatient.gender() != null) {
            user.setGender(updateDtoPatient.gender());
        }
        if (updateDtoPatient.healthcareProviderId() != null) {
            patientData.setHealthcareProviderId(updateDtoPatient.healthcareProviderId());
        }
        if (updateDtoPatient.socialSecurityNumber() != null) {
            patientData.setSocialSecurityNumber(updateDtoPatient.socialSecurityNumber());
        }

        this.patientDataRepository.save(patientData);
        this.userRepository.save(user);
        return userMapper.entityToReadDtoPatient(user, patientData);
    }

}
