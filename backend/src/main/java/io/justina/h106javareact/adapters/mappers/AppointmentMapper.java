package io.justina.h106javareact.adapters.mappers;
import io.justina.h106javareact.adapters.dtos.appointment.CreateDtoAppointment;
import io.justina.h106javareact.adapters.dtos.appointment.ReadDtoAppointment;
import io.justina.h106javareact.domain.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

        Appointment createAppointmentToEntity (CreateDtoAppointment createDtoAppointment);

        @Mapping(target = "patientId", source = "appointment.patient")
        @Mapping(target = "doctorId", source = "appointment.doctor")
        @Mapping(target = "relativeId", source = "appointment.relative")
        ReadDtoAppointment entityToReadAppointment(Appointment appointment);

        List<ReadDtoAppointment> entityListToReadAppointmentList (List<Appointment> appointmentList);


}
