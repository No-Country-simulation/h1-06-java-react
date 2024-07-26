package io.justina.h106javareact.adapters.dtos.medicalInformation;

import java.util.List;

public record AddDtoMedicalInformation(
        String id,
        Double weight,
        List<String> antigenList,
        List<String> antibodyList
) {
}
