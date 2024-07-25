package io.justina.h106javareact.adapters.mappers;

import io.justina.h106javareact.adapters.dtos.medicine.ReadDtoMedicine;
import io.justina.h106javareact.domain.entities.Medicine;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    ReadDtoMedicine medicineToReadDto (Medicine medicine);
    List<ReadDtoMedicine> medicineListToReadDtoList (List<Medicine> medicineList);
}
