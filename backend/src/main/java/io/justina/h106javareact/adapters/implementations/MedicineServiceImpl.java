package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.medicine.ReadDtoMedicine;
import io.justina.h106javareact.adapters.mappers.MedicineMapper;
import io.justina.h106javareact.adapters.repositories.MedicineRepository;
import io.justina.h106javareact.application.services.MedicineService;
import io.justina.h106javareact.domain.entities.Medicine;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;


    @Override
    public List<ReadDtoMedicine> readAll() {
        List<Medicine> entityList = medicineRepository.findAll();
        return medicineMapper.medicineListToReadDtoList(entityList);
    }
/*
    public Boolean scrapForMedicines(){
        String url = "https://www.anmat.gob.ar/atc/CodigosATC.asp";

        try{
            Document document = Jsoup.connect(url).get();
            Elements scrapedContent = document.select("#ContenedorCodigos > *");

            for(Element register : scrapedContent){
                if (!register.hasClass("StrCodigo4")) { continue; }
                var code = register.text();
                var name = register.nextElementSibling().text();

                if (!medicineRepository.existsByCode(code)) {
                Medicine medicine = new Medicine();
                medicine.setCode(code);
                medicine.setName(name);
                medicineRepository.save(medicine);
                }
            }

        } catch (IOException e) {
            return false;
        }
        return true;
    }
*/
}

