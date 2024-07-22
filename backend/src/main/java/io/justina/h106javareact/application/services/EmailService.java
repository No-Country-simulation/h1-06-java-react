package io.justina.h106javareact.application.services;

import io.justina.h106javareact.domain.entities.Appointment;
import org.apache.coyote.BadRequestException;

public interface EmailService {

    void sendUserRegistrationMail(String email) ;
    //void sendScheduledAppointments();
    //void sendAppointmentEmail (Appointment appointment);
    void emailConfirmation(String email, String userName) throws Exception;
    String validateToken(String tokenController, String email) throws BadRequestException;
    //void sendPasswordRecoveryMail(String email);
}
