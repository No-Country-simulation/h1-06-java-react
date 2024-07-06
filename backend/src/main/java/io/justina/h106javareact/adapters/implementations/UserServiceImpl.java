package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.user.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.user.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.user.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.user.UpdateDtoPatient;
import io.justina.h106javareact.adapters.mappers.PatientMapper;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.services.UserService;
import io.justina.h106javareact.application.validations.SelfValidation;
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
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;
    public final SelfValidation selfValidation;

    @Transactional
    @Override
    public ReadDtoPatient createPatient(CreateDtoPatient createDtoPatient) {
        var userAlreadyExists = userRepository.findByEmail(createDtoPatient.email());
        if(userAlreadyExists.isPresent()){ throw new EntityExistsException("¡Este email ya está en uso!"); }

        User user = this.patientMapper.createDtoToPatient(createDtoPatient);
        user.setPassword(passwordEncoder.encode(createDtoPatient.password()));

        user.setActive(Boolean.TRUE);
        user.setRole(Role.PACIENTE);
        var patientAdded = userRepository.save(user);
        return patientMapper.patientToReadDto(patientAdded);
    }

    @Override
    public ReadDtoPatient readPatientById(String id, Boolean active) {
        User user = userRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + id));

        return patientMapper.patientToReadDto(user);
    }

    @Override
    public ReadDtoPatient readPatientByEmail(String email, Boolean active) {
        User user = userRepository.findByEmailAndActive(email, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el email " + email));

        return patientMapper.patientToReadDto(user);
    }

    @Transactional
    @Override
    public ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoPatient) {
        selfValidation.checkSelfValidation(updateDtoPatient.id());
        User user = userRepository.findByIdAndActive(updateDtoPatient.id(), true)
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

        this.userRepository.save(user);
        return patientMapper.patientToReadDto(user);
    }

    @Transactional
    @Override
    public Boolean updatePassword(UpdateDtoPassword updateDtoPassword) {
        return null; }


    @Transactional
    @Override
    public Boolean togglePatient(String id) {
        selfValidation.checkSelfValidation(id);
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + id));
        userEntity.setActive(!userEntity.getActive());
        userRepository.save(userEntity);
        return true;
    }
}
