package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.repositories.AppointmentRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.application.services.EmailService;
import io.justina.h106javareact.domain.entities.Appointment;
import io.justina.h106javareact.domain.entities.User;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl extends HttpServlet implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    String token = token();
    @Value("${server.root}")
    private String serverRoot;
    private String rootPath;

    // EMAIL CONFIRMATION METHODS.
    public void emailConfirmation(String email, String userName) throws Exception {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("JUSTINA IO : Confirma tu correo.");

            Context context = new Context();
            context.setVariable("nombreUsuario", userName);
            context.setVariable("validationLink", buildEndpoint(email));
            String htmlBody = templateEngine.process("emailValidation", context);

            helper.setText(htmlBody, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new Exception("Error en la validación del correo electrónico: " + e.getMessage());
        }
    }

    private String buildEndpoint(String email) {
        String personalizedUrl = serverRoot + "/api/v1/email/emailValidation/"
                + token + "/" + email ;
        return personalizedUrl;
    }

    private String token() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    @Transactional
    private void sendUserRegistrationMail(String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Resgistrado con Exito!");

            Context context = new Context();
            // Agregar variables de contexto para la plantilla Thymeleaf
            context.setVariable("message", "Gracias por registrarse en Justina IO. Te registraste con el mail" + email);

            // Procesar la plantilla Thymeleaf
            String htmlBody = templateEngine.process("templateWelcomeRegister", context);

            // Establecer el cuerpo del mensaje como HTML
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }

    @Transactional
    public String validateToken(String tokenController, String email) throws BadRequestException {
        if (tokenController.equals(token)) {
            Optional<User> userValidating = userRepository.findByEmail(email);
            if (userValidating.isPresent()) {
                userValidating.get().setValidUser(true);
                userRepository.save(userValidating.get());
                return userValidating.get().getName() + " Confirmacion de identidad exitosa. ";
            } else {
                throw new RuntimeException("Usuario no validado!");
            }
        }
        throw new BadRequestException("¡Token no válido!");
    }

    // APPOINTMENT REMINDER METHODS.
    @Override
    public void sendAppointmentEmail(Appointment appointment) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(appointment.getPatient().getEmail());
            if (appointment.getRelative() != null) {
                helper.setTo(appointment.getRelative().getEmail());
            }
            helper.setSubject("Recordatorio de cita medica - JUSTINA IO");

            Context context = new Context();
            context.setVariable("nameUser", appointment.getPatient().getName() + " " + appointment.getPatient().getSurname());
            context.setVariable("appointmentDate", appointment.getDate().toLocalDate());
            context.setVariable("appointmentTime", appointment.getDate().toLocalTime());
            context.setVariable("appointmentDoctor", appointment.getDoctor().getName() + " " + appointment.getDoctor().getSurname());
            context.setVariable("appointmentAddress", appointment.getDoctor().getAddress());

            String htmlBody = templateEngine.process("templateScheduledAppointment", context);
            helper.setText(htmlBody, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo : " + e.getMessage(), e);
        }
    }

    @Override
    @Scheduled(cron = "0 0 8 * * ?")
    public void sendScheduledAppointments() {
        getAppointmentsToSendReminders().forEach(appointment -> {
            sendAppointmentEmail(appointment);
        });
    }

    public List<Appointment> getAppointmentsToSendReminders() {
        LocalDate today = LocalDate.now();
        LocalDateTime startDataTime = today.plusDays(2).atStartOfDay();
        LocalDateTime endDataTime = today.plusDays(3).atStartOfDay();
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<Appointment> newAppointmentList = appointmentList.stream().filter(appointment -> {
            return appointment.getDate().isAfter(startDataTime)
                    && appointment.getDate().isBefore(endDataTime);
        }).toList();
        return newAppointmentList;
    }

/*
// LÓGICA DEL MAIL VALIDATION.
 * 1. se registra exitosamente.
 * 2. generar token dentro de confirmationEmail() y se manda el mail.
 * 3. el usuario ve el email y toca el boton con un url personalizado.
 * 4. llega a nuestro controlador con el token por parametro.
 * 5. compara el token con el token del servidor.
 * */

    @Override
    public void sendPasswordRecoveryMail(String email, String temporalPassword) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Recuperación de contraseña");

            Context context = new Context();
            context.setVariable("messageRecovery", "Su nueva contraseña temporal es :   " + temporalPassword);

            String htmlBody = templateEngine.process("templateForgetPassword", context);
            helper.setText(htmlBody, true);
            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo de recuperacion, reenvie el correo : " + e.getMessage(), e);
        }
    }
}

