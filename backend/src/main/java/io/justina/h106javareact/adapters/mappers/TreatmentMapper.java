package io.justina.h106javareact.adapters.mappers;

import io.justina.h106javareact.adapters.dtos.treatment.CreateDtoTreatment;
import io.justina.h106javareact.adapters.dtos.treatment.ReadDtoTreatment;
import io.justina.h106javareact.domain.entities.Treatment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    Treatment createTreatmentToEntity (CreateDtoTreatment createDtoTreatment);
    ReadDtoTreatment entityToReadTreatment (Treatment treatment);
    List<ReadDtoTreatment> entityListToReadTreatmentList (List<Treatment> treatment);
}
