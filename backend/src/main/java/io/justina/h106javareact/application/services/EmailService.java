package io.justina.h106javareact.application.services;

import io.justina.h106javareact.domain.entities.Appointment;
import org.apache.coyote.BadRequestException;

public interface EmailService {


    void emailConfirmation(String email, String userName) throws Exception;
    String validateToken(String tokenController, String email) throws BadRequestException;
    void sendScheduledAppointments();
    void sendAppointmentEmail (Appointment appointment);
    void sendPasswordRecoveryMail(String email, String temporalPassword);
}
