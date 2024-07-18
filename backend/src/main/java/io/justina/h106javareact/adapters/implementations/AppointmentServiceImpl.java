package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.appointment.CreateDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.ReadDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.UpdateDtoAppointment;
import io.justina.h106javareact.adapters.exceptions.AppointmentAvailabilityException;
import io.justina.h106javareact.adapters.mappers.AppointmentMapper;
import io.justina.h106javareact.adapters.repositories.*;
import io.justina.h106javareact.application.services.AppointmentService;
import io.justina.h106javareact.application.validations.Validations;
import io.justina.h106javareact.domain.entities.Appointment;
import io.justina.h106javareact.domain.entities.DoctorData;
import io.justina.h106javareact.domain.entities.User;
import io.justina.h106javareact.domain.entities.enums.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorDataRepository doctorDataRepository;
    private final AppointmentMapper appointmentMapper;
    private final Validations validations;

    @Transactional
    @Override
    public ReadDtoAppointment create(CreateDtoAppointment createDtoAppointment) {
        if (validations.checkDoctorHourRange(createDtoAppointment.doctorId(),
                createDtoAppointment.date())) {
            validations.checkPatientAvailability(createDtoAppointment.patientId(), createDtoAppointment.date());
            validations.checkDoctorAvailability(createDtoAppointment.doctorId(), createDtoAppointment.date());
        } else {
            throw new AppointmentAvailabilityException("El doctor no trabaja en ese horario");
        }

        Appointment appointment = appointmentMapper.createAppointmentToEntity(
                createDtoAppointment);

        User patient = userRepository.findByIdAndActive(createDtoAppointment.patientId(), true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id "
                        + createDtoAppointment.patientId()));
        if (patient.getRole().equals(Role.PACIENTE)) {
            appointment.setPatient(patient);
        }

        User doctor = userRepository.findByIdAndActive(createDtoAppointment.doctorId(), true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id "
                        + createDtoAppointment.doctorId()));
        if (doctor.getRole().equals(Role.DOCTOR)) {
            appointment.setDoctor(doctor);
        }

        if (createDtoAppointment.relativeId() != null) {
            User relative = userRepository.findByIdAndActive(createDtoAppointment.relativeId(), true)
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el tutor con el id "
                            + createDtoAppointment.relativeId()));
            if (relative.getRole().equals(Role.TUTOR)) {
                appointment.setRelative(relative);
            }

        }

        appointment.setActive(true);
        var savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.entityToReadAppointment(savedAppointment);
    }

    @Override
    public ReadDtoAppointment update(UpdateDtoAppointment updateDtoAppointment)
            throws BadRequestException {
        Appointment appointment = appointmentRepository.findById(updateDtoAppointment.id())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el turno con el id "
                        + updateDtoAppointment.id()));

        if (updateDtoAppointment.date() != null) {
            if (validations.checkDoctorHourRange(appointment.getDoctor().getId(), updateDtoAppointment.date())) {
                validations.checkPatientAvailability(appointment.getPatient().getId(), updateDtoAppointment.date());
                validations.checkDoctorAvailability(appointment.getDoctor().getId(), updateDtoAppointment.date());
            } else {
                throw new AppointmentAvailabilityException("El doctor no trabaja en ese horario");
            }

            if (appointment.getDate().plusDays(1).isAfter(LocalDateTime.now())) {
                appointment.setDate(updateDtoAppointment.date());
            }

            throw new BadRequestException("No puede cambiar la fecha del turno dado que el mismo " +
                    "será en menos de 24hs. Deberá abonar el turno actual y solicitar un nuevo turno.");
        }
        if (updateDtoAppointment.observations() != null){
            appointment.setObservations(updateDtoAppointment.observations());
        }

        var savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.entityToReadAppointment(savedAppointment);

    }

    @Override
    public ReadDtoAppointment findByIdAndActive(String id, Boolean active) {
        var appointment = appointmentRepository.findByIdAndActive(id, active);
        return appointmentMapper.entityToReadAppointment(appointment);
    }

    @Override
    public List<ReadDtoAppointment> findByDoctorId(String doctorId, Boolean active) {
        validations.checkSelfValidation(doctorId);
        var appointmentList = appointmentRepository.findAppointmentsByDoctorIdAndActive(
                doctorId, active);
        return appointmentMapper.entityListToReadAppointmentList(appointmentList);
    }

    @Override
    public List<ReadDtoAppointment> findByPatientId(String patientId, Boolean active) {
        var appointmentList = appointmentRepository.findAppointmentsByPatientIdAndActive(
                patientId, active);
        return appointmentMapper.entityListToReadAppointmentList(appointmentList);
    }

    @Override
    public List<ReadDtoAppointment> findByDoctorIdAndDate(
            String doctorId, LocalDateTime date, Boolean active) {
        validations.checkSelfValidation(doctorId);
        var appointmentList = appointmentRepository.findAppointmentsByDoctorIdAndDate(
                doctorId, date, active);
        return appointmentMapper.entityListToReadAppointmentList(appointmentList);
    }

    @Override
    public List<ReadDtoAppointment> findByPatientIdAndDate(
            String patientId, LocalDateTime date, Boolean active) {
        var appointmentList = appointmentRepository.findAppointmentsByPatientIdAndDate(
                patientId, date, active);
        return appointmentMapper.entityListToReadAppointmentList(appointmentList);
    }

    @Override
    public List<ReadDtoAppointment> findByDoctorIdAndDateRange(
            String doctorId, LocalDateTime startDate, LocalDateTime endDate, Boolean active) {
        validations.checkSelfValidation(doctorId);
        var appointmentList = appointmentRepository.findAppointmentsByDoctorIdAndDateRange(
                doctorId, startDate, endDate, active);
        return appointmentMapper.entityListToReadAppointmentList(appointmentList);
    }

    @Override
    public List<ReadDtoAppointment> findByPatientIdAndDateRange(
            String patientId, LocalDateTime startDate, LocalDateTime endDate, Boolean active) {
        var appointmentList = appointmentRepository.findAppointmentsByPatientIdAndDateRange(
                patientId, startDate, endDate, active);
        return appointmentMapper.entityListToReadAppointmentList(appointmentList);
    }

    @Override
    public List<LocalDateTime> findByDoctorIdAndDateAvailable(String id, LocalDateTime startDate, LocalDateTime endDate) {
        User doctor = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + id));
        String dataId = doctor.getDoctorDataId();
        DoctorData doctorData = doctorDataRepository.findById(dataId)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id " + id));
        var notAvailableAppointments = appointmentRepository.findAppointmentsByDoctorIdAndDateRange(
                id, startDate, endDate, true);

        List<LocalDateTime> notAvailableList = new ArrayList<>();
        for (Appointment appointment : notAvailableAppointments) {
            notAvailableList.add(appointment.getDate());
        }

        LocalDate dateWithoutTime = startDate.toLocalDate();
        List<LocalDateTime> totalList = new ArrayList<>();
        List<LocalDateTime> availableList = new ArrayList<>();

        if (doctorData.getMorning()) {
            LocalTime startTime = LocalTime.of(8, 0);
            LocalTime endTime = LocalTime.of(11, 45);
            for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusMinutes(15)) {
                totalList.add(LocalDateTime.of(dateWithoutTime, time));
            }
        }
        if (doctorData.getAfternoon()){
            LocalTime startTime = LocalTime.of(12, 0);
            LocalTime endTime = LocalTime.of(16, 45);
            for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusMinutes(15)) {
                totalList.add(LocalDateTime.of(dateWithoutTime, time));
            }
        }
        if (doctorData.getEvening()){
            LocalTime startTime = LocalTime.of(17, 0);
            LocalTime endTime = LocalTime.of(21, 45);
            for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusMinutes(15)) {
                totalList.add(LocalDateTime.of(dateWithoutTime, time));
            }
        }

        for (LocalDateTime time : totalList) {
            boolean isBusy = false;
            for (LocalDateTime busy : notAvailableList) {
                if (time.equals(busy)) {
                    isBusy = true;
                    break;
                }
            }
            if (!isBusy && !availableList.contains(time)) {
                availableList.add(time);
            }
        }
        return availableList;
        }

    @Override
    public Boolean toggle(String id) throws BadRequestException {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el turno con el id " + id));

        LocalDateTime appointmentDate = appointment.getDate();
        LocalDateTime requiredDifference = LocalDateTime.now().plusHours(23).plusMinutes(59);

        if (appointment.getActive() == true) {
            if (appointmentDate.isAfter(requiredDifference)) {
                validations.checkSelfValidation(appointment.getPatient().getId());
                appointment.setActive(false);
                appointmentRepository.save(appointment);
                return true;
            } else {
                throw new BadRequestException("No puede cancelar un turno con menos de 24hs" +
                        " de antelación. Deberá abonarlo y volver a programar otro turno, de ser necesario.");
            }
        } else {
            appointment.setActive(true);
            appointmentRepository.save(appointment);
            return true;
        }
    }

}
