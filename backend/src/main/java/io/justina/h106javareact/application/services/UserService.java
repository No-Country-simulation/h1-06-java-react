package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.login.RequestLogin;
import io.justina.h106javareact.adapters.dtos.login.ResponseLogin;
import io.justina.h106javareact.adapters.dtos.login.UpdateDtoPassword;
import io.justina.h106javareact.adapters.dtos.patient.ReadDtoPatient;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
    ResponseLogin login(RequestLogin data);
    Boolean updatePassword (UpdateDtoPassword updateDtoPassword);
    <T> T readById(String id, Boolean active);
    <T> T readByEmail(String email, Boolean active);
    List<ReadDtoPatient> readBySurname(String surname, Boolean active);
    Boolean toggle(String id);
    Boolean temporalPassword(String email) throws BadRequestException;

    }
