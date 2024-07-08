package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.UpdateDtoRelative;
import io.justina.h106javareact.adapters.mappers.UserMapper;
import io.justina.h106javareact.adapters.repositories.DoctorDataRepository;
import io.justina.h106javareact.adapters.repositories.RelativeDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.services.DoctorService;
import io.justina.h106javareact.application.services.RelativeService;
import io.justina.h106javareact.application.validations.SelfValidation;
import io.justina.h106javareact.domain.entities.DoctorData;
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
public class RelativeServiceImpl implements RelativeService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RelativeDataRepository relativeDataRepository;
    private final PasswordEncoder passwordEncoder;
    public final SelfValidation selfValidation;

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
        user.setRelativeDataId(relativeDataAdded.getId());
        var relativeAdded = userRepository.save(user);
        return userMapper.entityToReadDtoRelative(relativeAdded, relativeDataAdded);
    }

    @Transactional
    @Override
    public ReadDtoRelative updateRelative(UpdateDtoRelative updateDtoRelative) {
            selfValidation.checkSelfValidation(updateDtoRelative.id());
            User user = userRepository.findByIdAndActive(updateDtoRelative.id(), true)
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el tutor con el id " + updateDtoRelative.id()));
            var relativeData = relativeDataRepository.findById(user.getRelativeDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el tutor con el id " + updateDtoRelative.id()));

            if (updateDtoRelative.name() != null) {
                user.setName(updateDtoRelative.name());
            }
            if (updateDtoRelative.surname() != null) {
                user.setSurname(updateDtoRelative.surname());
            }
            if (updateDtoRelative.dni() != null) {
                user.setDni(updateDtoRelative.dni());
            }
            if (updateDtoRelative.dateOfBirth() != null) {
                user.setDateOfBirth(updateDtoRelative.dateOfBirth());
            }
            if (updateDtoRelative.gender() != null) {
                user.setGender(updateDtoRelative.gender());
            }
            if (updateDtoRelative.assistedEmail()!= null) {
                relativeData.setAssistedEmail(updateDtoRelative.assistedEmail());
            }

            this.relativeDataRepository.save(relativeData);
            this.userRepository.save(user);
            return userMapper.entityToReadDtoRelative(user, relativeData);
        }

    }

