package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.login.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.adapters.mappers.UserMapper;
import io.justina.h106javareact.adapters.repositories.DoctorDataRepository;
import io.justina.h106javareact.adapters.repositories.PatientDataRepository;
import io.justina.h106javareact.adapters.repositories.RelativeDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.validations.SelfValidation;
import io.justina.h106javareact.domain.entities.DoctorData;
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
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatientDataRepository patientDataRepository;
    private final DoctorDataRepository doctorDataRepository;
    private final RelativeDataRepository relativeDataRepository;
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
    public ReadDtoDoctor createDoctor(CreateDtoDoctor createDtoDoctor) {
        var userAlreadyExists = userRepository.findByEmail(createDtoDoctor.email());
        if(userAlreadyExists.isPresent()){ throw new EntityExistsException("¡Este email ya está en uso!"); }

        DoctorData doctorData = this.userMapper.createDoctorDtoToData(createDtoDoctor);
        var doctorDataAdded = doctorDataRepository.save(doctorData);

        User user = this.userMapper.createDoctorDtoToUser(createDtoDoctor);
        user.setPassword(passwordEncoder.encode(createDtoDoctor.password()));

        user.setActive(Boolean.TRUE);
        user.setRole(Role.DOCTOR);
        user.setDoctorDataId(doctorDataAdded.getId());
        var doctorAdded = userRepository.save(user);
        return userMapper.entityToReadDtoDoctor(doctorAdded, doctorDataAdded);
    }

    @Transactional
    @Override
    public ReadDtoRelative createRelative(CreateDtoRelative createDtoRelative) {
        var userAlreadyExists = userRepository.findByEmail(createDtoRelative.email());
        if(userAlreadyExists.isPresent()){ throw new EntityExistsException("¡Este email ya está en uso!"); }

        RelativeData relativeData = this.userMapper.createRelativeDtoToData(createDtoRelative);
        var relativeDataAdded = relativeDataRepository.save(relativeData);

        User user = this.userMapper.createRelativeDtoToUser(createDtoRelative);
        user.setPassword(passwordEncoder.encode(createDtoRelative.password()));

        user.setActive(Boolean.TRUE);
        user.setRole(Role.TUTOR);
        user.setDoctorDataId(relativeDataAdded.getId());
        var relativeAdded = userRepository.save(user);
        return userMapper.entityToReadDtoRelative(relativeAdded, relativeDataAdded);
    }

    @Override
    public ReadDtoPatient readPatientById(String id, Boolean active) {
        User user = userRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + id));

        return null; //userMapper.patientToReadDto(user);
    }

    @Override
    public ReadDtoPatient readPatientByEmail(String email, Boolean active) {
        User user = userRepository.findByEmailAndActive(email, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el email " + email));

        return null; //userMapper.patientToReadDto(user);
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
        return null; //userMapper.patientToReadDto(user);
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
