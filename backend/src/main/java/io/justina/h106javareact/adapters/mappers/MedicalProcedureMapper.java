package io.justina.h106javareact.adapters.mappers;
import io.justina.h106javareact.adapters.dtos.medicalProcedure.ReadDtoMedicalProcedure;
import io.justina.h106javareact.domain.entities.MedicalProcedure;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalProcedureMapper {
    ReadDtoMedicalProcedure medicalProcedureToReadDto (MedicalProcedure medicalProcedure);
    List<ReadDtoMedicalProcedure> medicalProcedureListToReadDtoList (List<MedicalProcedure> medicalProceduresList);
}
