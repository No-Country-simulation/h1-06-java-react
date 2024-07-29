package io.justina.h106javareact.adapters.dtos.treatment;

import io.justina.h106javareact.domain.entities.enums.DonationType;

public record DonationDtoResult(
        DonationType donationType,
        String donorId,
        String crossedPatientId,
        String crossedDonorId
) {
}
