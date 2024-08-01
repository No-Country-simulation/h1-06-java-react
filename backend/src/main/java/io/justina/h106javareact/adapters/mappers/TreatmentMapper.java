package io.justina.h106javareact.adapters.mappers;

import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.domain.entities.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    Treatment createTreatmentToEntity (CreateDtoTreatment createDtoTreatment);
    @Mapping(target = "patientId", source = "treatment.patient.id")
    @Mapping(target = "donorId", source = "treatment.donationData.donorId")
    @Mapping(target = "donationType", source = "treatment.donationData.donationType")
    ReadDtoTreatment entityToReadTreatment (Treatment treatment);
    List<ReadDtoTreatment> entityListToReadTreatmentList (List<Treatment> treatment);
}
