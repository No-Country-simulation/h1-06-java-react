package io.justina.h106javareact.application.services;
import io.justina.h106javareact.adapters.dtos.medicine.ReadDtoMedicine;
import java.util.List;

public interface MedicineService {

    List<ReadDtoMedicine> readAll();
    Boolean scrapForMedicines();
}
