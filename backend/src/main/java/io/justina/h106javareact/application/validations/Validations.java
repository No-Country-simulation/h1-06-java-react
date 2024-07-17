package io.justina.h106javareact.application.validations;

import io.justina.h106javareact.adapters.exceptions.AppointmentAvailabilityException;
import io.justina.h106javareact.adapters.repositories.AppointmentRepository;
import io.justina.h106javareact.adapters.repositories.DoctorDataRepository;
import io.justina.h106javareact.adapters.repositories.UserRepository;
import io.justina.h106javareact.domain.entities.Appointment;
import io.justina.h106javareact.domain.entities.User;
import io.justina.h106javareact.domain.entities.enums.MedicalLicense;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Validations {

    private final UserRepository userRepository;
    private final DoctorDataRepository doctorDataRepository;
    private final AppointmentRepository appointmentRepository;

    public boolean checkSelfValidation(String id) {
        String userLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findById(id);
        if (user.isEmpty() || !userLogged.equals(user.get().getEmail())) {
            throw new IllegalArgumentException("¡El usuario no tiene permiso para esta acción!");
        }
        return true;
    }

    public User userAlreadyExistsValidation(String email) {
        var userAlreadyExists = userRepository.findByEmail(email);
        if (userAlreadyExists.isPresent() && userAlreadyExists.get().getActive()) {
            throw new EntityExistsException("¡Este email ya está en uso!");
        }
        if (userAlreadyExists.isPresent() && !userAlreadyExists.get().getActive()) {
            return userAlreadyExists.get();
        }
        return null;
    }

    public void licenseAlreadyExistValidation(MedicalLicense licensePlace, String medicalLicense) {
        var licenseAlreadyExists = doctorDataRepository.findByLicensePlaceAndMedicalLicense(
                licensePlace, medicalLicense);
        if (licenseAlreadyExists != null) {
            throw new EntityExistsException("¡Esta licencia médica corresponde a otro profesional!" +
                    " Corrobore número y tipo");
        }
    }

    public boolean checkDoctorHourRange(String doctorId, LocalDateTime dateTime) {
        LocalTime appointmentTime = dateTime.toLocalTime();

        LocalTime morningStart = LocalTime.parse("07:59");
        LocalTime morningEnd = LocalTime.parse("12:00");
        LocalTime afternoonStart = LocalTime.parse("11:59");
        LocalTime afternoonEnd = LocalTime.parse("17:00");
        LocalTime eveningStart = LocalTime.parse("16:59");
        LocalTime eveningEnd = LocalTime.parse("21:59");

        User doctor = userRepository.findByIdAndActive(doctorId, true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + doctorId));
        var doctorData = doctorDataRepository.findById(doctor.getDoctorDataId())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + doctorId));

        boolean check = false;
        if (doctorData.getMorning() != null){
            if (doctorData.getMorning() &&
                    (appointmentTime.isAfter(morningStart) &&
                            appointmentTime.isBefore(morningEnd))){
                check = true;
            }
        }
        if(doctorData.getAfternoon() != null) {
            if (doctorData.getAfternoon() &&
                    (appointmentTime.isAfter(afternoonStart) &&
                            appointmentTime.isBefore(afternoonEnd))) {
                check = true;
            }
        }
        if(doctorData.getEvening() != null) {
            if (doctorData.getEvening() &&
                    (appointmentTime.isAfter(eveningStart) &&
                            appointmentTime.isBefore(eveningEnd))) {
                check = true;
            }
        }
        return check;
    }

    public void checkPatientAvailability(String patientId, LocalDateTime newDateStarts){
        LocalDateTime newDateEnds = newDateStarts.plusMinutes(15);
        LocalDateTime beforeStartValidation = newDateStarts.minusMinutes(14).minusSeconds(59);

        List<Appointment> appointmentList = appointmentRepository.
                findAppointmentsByPatientIdAndDateRange(patientId,
                        beforeStartValidation, newDateEnds.minusSeconds(1), true);

        if(!appointmentList.isEmpty()){
                throw new AppointmentAvailabilityException("El paciente con el id: " + patientId +
                        " tiene otro turno en el mismo horario.");
            }
        }

    public void checkDoctorAvailability(String doctorId, LocalDateTime newDateStarts){
        LocalDateTime newDateEnds = newDateStarts.plusMinutes(15);
        LocalDateTime beforeStartValidation = newDateStarts.minusMinutes(14).minusSeconds(59);

        List<Appointment> appointmentList = appointmentRepository.
                findAppointmentsByDoctorIdAndDateRange(doctorId,
                        beforeStartValidation, newDateEnds.minusSeconds(1), true);

        if(appointmentList.size() > 0){
            throw new AppointmentAvailabilityException("El profesional con el id: " + doctorId +
                    " tiene otro turno en el mismo horario.");
        }
    }

    }




