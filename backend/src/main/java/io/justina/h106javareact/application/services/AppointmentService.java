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
    ReadDtoAppointment findByIdAndActive(String id, Boolean active);
    List<ReadDtoAppointment> findByDoctorId(String id, Boolean active);
    List<ReadDtoAppointment> findByPatientId(String id, Boolean active);
    List<ReadDtoAppointment> findByDoctorIdAndDate(String id, LocalDateTime date, Boolean active);
    List<ReadDtoAppointment> findByPatientIdAndDate(String id, LocalDateTime date, Boolean active);
    List<ReadDtoAppointment> findByDoctorIdAndDateRange(String id, LocalDateTime startDate, LocalDateTime endDate, Boolean active);
    List<ReadDtoAppointment> findByPatientIdAndDateRange(String id, LocalDateTime startDate, LocalDateTime endDate, Boolean active);
}
