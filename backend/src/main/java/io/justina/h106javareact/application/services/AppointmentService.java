package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.appointment.CreateDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.ReadDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.UpdateDtoAppointment;
import org.apache.coyote.BadRequestException;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    ReadDtoAppointment create(CreateDtoAppointment createDtoAppointment);
    ReadDtoAppointment update(UpdateDtoAppointment updateDtoAppointment) throws BadRequestException;
    List<ReadDtoAppointment> findByDoctorIdAndDateRange(String doctorId, LocalDateTime startDate, LocalDateTime endDate, Boolean active);
    List<ReadDtoAppointment> findByPatientIdAndDateRange(String patientId, LocalDateTime startDate, LocalDateTime endDate, Boolean active);
}
