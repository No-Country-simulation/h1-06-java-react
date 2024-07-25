package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.medicalInformation.AddDtoMedicalInformation;
import io.justina.h106javareact.adapters.dtos.patient.CreateDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.dtos.patient.UpdateDtoPatient;
import io.justina.h106javareact.adapters.mappers.UserMapper;
import io.justina.h106javareact.adapters.repositories.*;
import io.justina.h106javareact.application.services.EmailService;
import io.justina.h106javareact.application.services.PatientService;
import io.justina.h106javareact.application.validations.Validations;
import io.justina.h106javareact.domain.entities.*;
import io.justina.h106javareact.domain.entities.enums.Role;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatientDataRepository patientDataRepository;
    private final RelativeDataRepository relativeDataRepository;
    private final PasswordEncoder passwordEncoder;
    public final Validations validations;
    public final EmailService emailService;
    public final AntigenRepository antigenRepository;
    public final AntibodyRepository antibodyRepository;

    @Transactional
    @Override
    public ReadDtoPatient createPatient(CreateDtoPatient createDtoPatient) throws Exception {
        var relativeAssociatedData = relativeDataRepository.findByAssistedEmail(createDtoPatient.email());
        User relative = null;
        PatientData patientData = this.userMapper.createPatientDtoToData(createDtoPatient);
        if (!relativeAssociatedData.isEmpty()) {
            relative = userRepository.findByRelativeDataId(relativeAssociatedData.get().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "No se puede encontrar el tutor para este paciente"));
            patientData.setRelativeDataId(relative.getId());
        }

        User isReturningToTheApp = validations.userAlreadyExistsValidation(createDtoPatient.email());

        if (isReturningToTheApp != null) {
            isReturningToTheApp.setActive(true);
            userRepository.save(isReturningToTheApp);
            return userMapper.entityToReadDtoPatient(isReturningToTheApp, patientData);
        }

        var patientDataAdded = patientDataRepository.save(patientData);

        User user = this.userMapper.createPatientDtoToUser(createDtoPatient);
        user.setPassword(passwordEncoder.encode(createDtoPatient.password()));

        user.setActive(Boolean.TRUE);
        user.setRole(Role.PACIENTE);
        user.setPatientDataId(patientDataAdded.getId());

        emailService.emailConfirmation(user.getEmail(), user.getName());

        var patientAdded = userRepository.save(user);
        return userMapper.entityToReadDtoPatient(patientAdded, patientDataAdded);
    }

    @Transactional
    @Override
    public ReadDtoPatient updatePatient(UpdateDtoPatient updateDtoPatient) {
        validations.checkRelativeValidation(updateDtoPatient.id());
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
        if (updateDtoPatient.email() != null) {
            validations.userAlreadyExistsValidation(updateDtoPatient.email());
            user.setEmail(updateDtoPatient.email());
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
        if (updateDtoPatient.address() != null) {
            user.setAddress(updateDtoPatient.address());
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

    @Transactional
    @Override
    public ReadDtoPatient updateMedicalInformation(AddDtoMedicalInformation medicalInformation) throws BadRequestException {
        User user = userRepository.findById(medicalInformation.id())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + medicalInformation.id()));
        if (!user.getActive()) { throw new BadRequestException("No se puede actualizar un paciente dado de baja"); }
        PatientData patientData = patientDataRepository.findById(user.getPatientDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + medicalInformation.id()));
            if (medicalInformation.weight() != null){
                patientData.setWeight(medicalInformation.weight());
            }
            if (!medicalInformation.antigenList().isEmpty()){
                List<String> stringList = medicalInformation.antigenList();
                List<Antigen> antigenList = new ArrayList<>();
                for (String type : stringList) {
                    Antigen antigen = new Antigen();
                    antigen.setType(type);
                    antigen.setPatientId(patientData);
                    Antigen savedAntigen = antigenRepository.save(antigen);
                    antigenList.add(savedAntigen);
                }
                patientData.setAntigenList(antigenList);
                }
            if (!medicalInformation.antibodyList().isEmpty()){
                List<String> stringList = medicalInformation.antibodyList();
                List<Antibody> antibodyList = new ArrayList<>();
                for (String type : stringList) {
                    Antibody antibody = new Antibody();
                    antibody.setType(type);
                    antibody.setPatientId(patientData);
                    Antibody savedAntibody = antibodyRepository.save(antibody);
                    antibodyList.add(savedAntibody);
                }
                patientData.setAntibodyList(antibodyList);
            }
            patientDataRepository.save(patientData);
            return userMapper.entityToReadDtoPatient(user, patientData);
        }

}
