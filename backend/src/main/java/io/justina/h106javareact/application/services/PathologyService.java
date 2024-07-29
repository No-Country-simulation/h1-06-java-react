package io.justina.h106javareact.application.services;
import io.justina.h106javareact.adapters.dtos.pathology.ReadDtoPathology;
import java.io.IOException;
import java.util.List;

public interface PathologyService {

    List<ReadDtoPathology> readAll();
    //Boolean scraping() throws IOException;
}
