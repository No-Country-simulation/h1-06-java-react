package io.justina.h106javareact.adapters.implementations;

import io.justina.h106javareact.adapters.dtos.medicalProcedure.ReadDtoMedicalProcedure;
import io.justina.h106javareact.adapters.mappers.MedicalProcedureMapper;
import io.justina.h106javareact.adapters.repositories.MedicalProcedureRepository;
import io.justina.h106javareact.application.services.MedicalProcedureService;
import io.justina.h106javareact.domain.entities.MedicalProcedure;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalProcedureServiceImpl implements MedicalProcedureService {

    private final MedicalProcedureRepository medicalProcedureRepository;
    private final MedicalProcedureMapper medicalProcedureMapper;

    @Override
    public List<ReadDtoMedicalProcedure> readAll() {
        List<MedicalProcedure> entityList = medicalProcedureRepository.findAll();
        return medicalProcedureMapper.medicalProcedureListToReadDtoList(entityList);
    }

    @Override
    public Boolean scraping() throws IOException {
        File file = ResourceUtils.getFile("classpath:PracticasMedicas.csv");
        List<MedicalProcedure> medicalProcedureList = new ArrayList<>();

        try (CSVParser parser = new CSVParser(new FileReader(file),
                CSVFormat.DEFAULT.withHeader("code", "name").withFirstRecordAsHeader())) {
            for (CSVRecord record : parser) {
                String code = record.get("code");
                String name = record.get("name");
                MedicalProcedure medicalProcedure = new MedicalProcedure();
                medicalProcedure.setCode(code);
                medicalProcedure.setName(name);
                medicalProcedureList.add(medicalProcedure);
            }
        }

        medicalProcedureRepository.saveAll(medicalProcedureList);
        return true;
    }
}
