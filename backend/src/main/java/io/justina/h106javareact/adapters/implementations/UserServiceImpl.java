package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.login.RequestLogin;
import io.justina.h106javareact.adapters.dtos.login.ResponseLogin;
import io.justina.h106javareact.adapters.dtos.login.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import io.justina.h106javareact.adapters.mappers.UserMapper;
import io.justina.h106javareact.adapters.repositories.DoctorDataRepository;
import io.justina.h106javareact.adapters.repositories.PatientDataRepository;
import io.justina.h106javareact.adapters.repositories.RelativeDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.services.UserService;
import io.justina.h106javareact.application.validations.Validations;
import io.justina.h106javareact.domain.entities.DoctorData;
import io.justina.h106javareact.domain.entities.PatientData;
import io.justina.h106javareact.domain.entities.RelativeData;
import io.justina.h106javareact.domain.entities.User;
import io.justina.h106javareact.domain.entities.enums.Role;
import io.justina.h106javareact.infrastructure.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DoctorDataRepository doctorDataRepository;
    private final PatientDataRepository patientDataRepository;
    private final RelativeDataRepository relativeDataRepository;
    private final UserMapper userMapper;
    public final Validations validations;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public ResponseLogin login(RequestLogin data) {
        var patient = userRepository.findByEmail(data.email())
                .orElseThrow(() -> new EntityNotFoundException("Email y/o contraseña incorrectos"));

        if (!patient.getActive()) {
            throw new EntityNotFoundException(
                    "El usuario correspondiente al email " + data.email() + " se encuentra dado de baja.");
        }
        var role = patient.getAuthorities().toString();
        var id = patient.getId();
        var token = jwtService.getToken(patient);
        var name = patient.getName();
        var surname = patient.getSurname();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(),
                        data.password()));

        ResponseLogin responseLogin = new ResponseLogin(token, role, id, name, surname);

        return responseLogin;
    }

    @Transactional
    @Override
    public Boolean updatePassword(UpdateDtoPassword updateDtoPassword) {
        return null;
    }

    @Override
    public <T> T readById(String id, Boolean active) {
        User user = userRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el usuario con el id " + id));

        if (user.getRole() == Role.DOCTOR) {
            DoctorData doctorData = doctorDataRepository.findById(user.getDoctorDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + id));
            return (T) userMapper.entityToReadDtoDoctor(user, doctorData);
        }

        if (user.getRole() == Role.PACIENTE) {
            PatientData patientData = patientDataRepository.findById(user.getPatientDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id " + id));
            return (T) userMapper.entityToReadDtoPatient(user, patientData);
        }

        if (user.getRole() == Role.TUTOR) {
            RelativeData relativeData = relativeDataRepository.findById(user.getRelativeDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el tutor con el id " + id));
            return (T) userMapper.entityToReadDtoRelative(user, relativeData);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public <T> T readByEmail(String email, Boolean active) {
        User user = userRepository.findByEmailAndActive(email, active)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el usuario con el email " + email));

        if (user.getRole() == Role.DOCTOR) {
            DoctorData doctorData = doctorDataRepository.findById(user.getDoctorDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el email " + email));
            return (T) userMapper.entityToReadDtoDoctor(user, doctorData);
        }

        if (user.getRole() == Role.PACIENTE) {
            PatientData patientData = patientDataRepository.findById(user.getPatientDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el email " + email));
            return (T) userMapper.entityToReadDtoPatient(user, patientData);
        }

        if (user.getRole() == Role.TUTOR) {
            RelativeData relativeData = relativeDataRepository.findById(user.getRelativeDataId())
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el tutor con el email " + email));
            return (T) userMapper.entityToReadDtoRelative(user, relativeData);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public List<ReadDtoPatient> readBySurname(String surname, Boolean active) {
        List<ReadDtoPatient> patientList = new ArrayList<>();
        List<User> userList = userRepository.findBySurnameAndActive(surname, active);

        for (User user : userList) {
            if (user.getRole() == Role.PACIENTE) {
                PatientData patientData = patientDataRepository.findById(user.getPatientDataId())
                        .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el apellido " + surname));
                ReadDtoPatient patient = userMapper.entityToReadDtoPatient(user, patientData);
                patientList.add(patient);
            }
        }
        return patientList;
    }

    @Transactional
    @Override
    public Boolean toggle(String id) {

        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el usuario con el id " + id));

        if (userEntity.getRelativeDataId() != null) {
            validations.checkRelativeValidation(id);
        } else {
            validations.checkSelfValidation(id);
        }

        userEntity.setActive(!userEntity.getActive());
        userRepository.save(userEntity);
        return true;
    }
}
