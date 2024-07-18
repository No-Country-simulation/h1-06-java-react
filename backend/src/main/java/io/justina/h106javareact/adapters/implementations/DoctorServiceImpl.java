package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.doctor.CreateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.ReadDtoDoctor;
import io.justina.h106javareact.adapters.dtos.doctor.UpdateDtoDoctor;
import io.justina.h106javareact.adapters.dtos.pathology.ReadDtoPathology;
import io.justina.h106javareact.adapters.mappers.UserMapper;
import io.justina.h106javareact.adapters.repositories.DoctorDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.services.DoctorService;
import io.justina.h106javareact.application.validations.Validations;
import io.justina.h106javareact.domain.entities.DoctorData;
import io.justina.h106javareact.domain.entities.Pathology;
import io.justina.h106javareact.domain.entities.User;
import io.justina.h106javareact.domain.entities.enums.MedicalLicense;
import io.justina.h106javareact.domain.entities.enums.Role;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DoctorDataRepository doctorDataRepository;
    private final PasswordEncoder passwordEncoder;
    public final Validations validations;

    @Transactional
    @Override
    public ReadDtoDoctor createDoctor(CreateDtoDoctor createDtoDoctor) {
        User isReturningToTheApp = validations.userAlreadyExistsValidation(createDtoDoctor.email());
        DoctorData doctorData = this.userMapper.createDoctorDtoToData(createDtoDoctor);

        if (isReturningToTheApp != null){
            isReturningToTheApp.setActive(true);
            userRepository.save(isReturningToTheApp);
            return userMapper.entityToReadDtoDoctor(isReturningToTheApp, doctorData);}

        validations.licenseAlreadyExistValidation(createDtoDoctor.licensePlace(), createDtoDoctor.medicalLicense());

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
    public ReadDtoDoctor updateDoctor(UpdateDtoDoctor updateDtoDoctor) {
        validations.checkSelfValidation(updateDtoDoctor.id());
        User user = userRepository.findByIdAndActive(updateDtoDoctor.id(), true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + updateDtoDoctor.id()));
        var doctorData = doctorDataRepository.findById(user.getDoctorDataId())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + updateDtoDoctor.id()));

        if (updateDtoDoctor.licensePlace() != null || updateDtoDoctor.medicalLicense() != null) {
            validations.licenseAlreadyExistValidation(updateDtoDoctor.licensePlace(),
                    updateDtoDoctor.medicalLicense()); }

        if (updateDtoDoctor.name() != null) {
            user.setName(updateDtoDoctor.name());
        }
        if (updateDtoDoctor.surname() != null) {
            user.setSurname(updateDtoDoctor.surname());
        }
        if (updateDtoDoctor.email() != null) {
            validations.userAlreadyExistsValidation(updateDtoDoctor.email());
            user.setEmail(updateDtoDoctor.email());
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
        if (updateDtoDoctor.address() != null) {
            user.setAddress(updateDtoDoctor.address());
        }
        if (updateDtoDoctor.specialty() != null) {
            doctorData.setSpecialty(updateDtoDoctor.specialty());
        }
        if (updateDtoDoctor.licensePlace() != null) {
            doctorData.setLicensePlace(updateDtoDoctor.licensePlace());
        }
        if (updateDtoDoctor.medicalLicense() != null) {
            doctorData.setMedicalLicense(updateDtoDoctor.medicalLicense());
        }

        this.doctorDataRepository.save(doctorData);
        this.userRepository.save(user);
        return userMapper.entityToReadDtoDoctor(user, doctorData);
    }

    @Override
    public List<ReadDtoDoctor> readBySpecialty(String specialty) {
        var doctors = userRepository.findByRoleAndActive(Role.DOCTOR, true);
        List<ReadDtoDoctor> specialistDto = new ArrayList<>();

        for (User user : doctors){
            var doctorDataId = user.getDoctorDataId();
            var doctorData = doctorDataRepository.findById(doctorDataId).get();
            if (doctorData.getSpecialty().toString().equals(specialty)){
                var doctorDto = userMapper.entityToReadDtoDoctor(user, doctorData);
                specialistDto.add(doctorDto);
            }
        }

        return specialistDto;
    }

}
