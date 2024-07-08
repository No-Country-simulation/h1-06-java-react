package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.UpdateDtoRelative;

public interface RelativeService {
    ReadDtoRelative createRelative(CreateDtoRelative createDtoRelative);
    ReadDtoRelative readRelativeById(String id, Boolean active);
    ReadDtoRelative readRelativeByEmail(String email, Boolean active);
    ReadDtoRelative updateRelative(UpdateDtoRelative updateDtoRelative);
    Boolean toggleRelative(String id);
}
