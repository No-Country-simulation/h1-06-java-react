package io.justina.h106javareact.adapters.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.justina.h106javareact.adapters.dtos.pathology.ReadDtoPathology;
import io.justina.h106javareact.adapters.mappers.PathologyMapper;
import io.justina.h106javareact.adapters.repositories.PathologyRepository;
import io.justina.h106javareact.application.services.PathologyService;
import io.justina.h106javareact.domain.entities.Medicine;
import io.justina.h106javareact.domain.entities.Pathology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PathologyServiceImpl implements PathologyService {

    private final PathologyRepository pathologyRepository;
    private final PathologyMapper pathologyMapper;

    @Override
    public List<ReadDtoPathology> readAll() {
        List<Pathology> entityList = pathologyRepository.findAll();
        return pathologyMapper.pathologyListToReadDtoList(entityList);
    }

    public Boolean scraping() throws IOException {
        String cwd = Path.of("").toAbsolutePath().toString();
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Pathology> pathologyList= mapper.readValue(
                new File(cwd + "/src/main/java/io/justina/h106javareact/domain/utils/cie10.json"),
                new TypeReference<List<Pathology>>(){});

        pathologyRepository.saveAll(pathologyList);
        return true;
    }
}