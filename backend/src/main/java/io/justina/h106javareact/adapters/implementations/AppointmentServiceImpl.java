package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.appointment.CreateDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.ReadDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.UpdateDtoAppointment;
import io.justina.h106javareact.adapters.mappers.AppointmentMapper;
import io.justina.h106javareact.adapters.repositories.*;
import io.justina.h106javareact.application.services.AppointmentService;
import io.justina.h106javareact.domain.entities.Appointment;
import io.justina.h106javareact.domain.entities.User;
import io.justina.h106javareact.domain.entities.enums.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PatientDataRepository patientDataRepository;
    private final RelativeDataRepository relativeDataRepository;
    private final DoctorDataRepository doctorDataRepository;
    private final AppointmentMapper appointmentMapper;

    @Transactional
    @Override
    public ReadDtoAppointment create(CreateDtoAppointment createDtoAppointment) {
        Appointment appointment = appointmentMapper.createAppointmentToEntity(createDtoAppointment);

        User patient = userRepository.findByIdAndActive(createDtoAppointment.patientId(), true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el paciente con el id "
                        + createDtoAppointment.patientId()));
        if (patient.getRole().equals(Role.PACIENTE)){ appointment.setPatient(patient); }

        User doctor = userRepository.findByIdAndActive(createDtoAppointment.doctorId(), true)
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el doctor con el id "
                        + createDtoAppointment.doctorId()));
        if (doctor.getRole().equals(Role.DOCTOR)){ appointment.setDoctor(doctor); }

        if (createDtoAppointment.relativeId() != null) {
            User relative = userRepository.findByIdAndActive(createDtoAppointment.relativeId(), true)
                    .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el tutor con el id "
                            + createDtoAppointment.relativeId()));
            if (relative.getRole().equals(Role.TUTOR)) {appointment.setRelative(relative); }

        }

        var savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.entityToReadAppointment(savedAppointment);
    }

    @Override
    public ReadDtoAppointment update(UpdateDtoAppointment updateDtoAppointment) throws BadRequestException {
        Appointment appointment = appointmentRepository.findById(updateDtoAppointment.id())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar el turno con el id "
                        + updateDtoAppointment.id()));

        if (appointment.getDate().plusDays(1).isAfter(LocalDateTime.now())) {
            appointment.setDate(updateDtoAppointment.date());
            var savedAppointment = appointmentRepository.save(appointment);
            return appointmentMapper.entityToReadAppointment(savedAppointment);
        }

        throw new BadRequestException("No puede cambiar la fecha del turno dado que el mismo será en menos de 24hs. Deberá abonar el turno actual y solicitar un nuevo turno.");
    }
}
