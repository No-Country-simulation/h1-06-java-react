package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.adapters.mappers.UserMapper;
import io.justina.h106javareact.adapters.repositories.DoctorDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.services.DoctorService;
import io.justina.h106javareact.application.validations.SelfValidation;
import io.justina.h106javareact.domain.entities.DoctorData;
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
public class DoctorServiceImpl implements DoctorService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DoctorDataRepository doctorDataRepository;
    private final PasswordEncoder passwordEncoder;
    public final SelfValidation selfValidation;

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
    public ReadDtoDoctor updateDoctor(UpdateDtoDoctor updateDtoDoctor)  {
            selfValidation.checkSelfValidation(updateDtoDoctor.id());
            User user = userRepository.findByIdAndActive(updateDtoDoctor.id(), true)
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + updateDtoDoctor.id()));
            var doctorData = doctorDataRepository.findById(user.getDoctorDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + updateDtoDoctor.id()));

            if (updateDtoDoctor.name() != null) {
                user.setName(updateDtoDoctor.name());
            }
            if (updateDtoDoctor.surname() != null) {
                user.setSurname(updateDtoDoctor.surname());
            }
            if (updateDtoDoctor.dni() != null) {
                user.setDni(updateDtoDoctor.dni());
            }
            if (updateDtoDoctor.dateOfBirth() != null) {
                user.setDateOfBirth(updateDtoDoctor.dateOfBirth());
            }
            if (updateDtoDoctor.gender() != null) {
                user.setGender(updateDtoDoctor.gender());
            }
            if (updateDtoDoctor.specialty()!= null) {
                doctorData.setSpecialty(updateDtoDoctor.specialty());
            }
            if (updateDtoDoctor.medicalLicense()!= null) {
                doctorData.setMedicalLicense(updateDtoDoctor.medicalLicense());
            }

            this.doctorDataRepository.save(doctorData);
            this.userRepository.save(user);
            return userMapper.entityToReadDtoDoctor(user, doctorData);
        }

    }
