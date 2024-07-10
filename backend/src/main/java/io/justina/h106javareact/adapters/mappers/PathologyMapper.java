package io.justina.h106javareact.adapters.mappers;

import io.justina.h106javareact.adapters.dtos.pathology.ReadDtoPathology;
import io.justina.h106javareact.domain.entities.Pathology;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PathologyMapper {
    ReadDtoPathology pathologyToReadDto (Pathology pathology);
    List<ReadDtoPathology> pathologyListToReadDtoList (List<Pathology> pathologyList);
}

