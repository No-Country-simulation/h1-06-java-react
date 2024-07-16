package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.appointment.CreateDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.ReadDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.UpdateDtoAppointment;
import org.apache.coyote.BadRequestException;

public interface AppointmentService {

    ReadDtoAppointment create(CreateDtoAppointment createDtoAppointment);
    ReadDtoAppointment update(UpdateDtoAppointment updateDtoAppointment) throws BadRequestException;
}
