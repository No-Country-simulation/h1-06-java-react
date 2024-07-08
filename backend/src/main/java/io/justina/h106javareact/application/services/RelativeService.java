package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.relative.CreateDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.ReadDtoRelative;
import io.justina.h106javareact.adapters.dtos.relative.UpdateDtoRelative;

public interface RelativeService {
    ReadDtoRelative createRelative(CreateDtoRelative createDtoRelative);
    ReadDtoRelative updateRelative(UpdateDtoRelative updateDtoRelative);
}
