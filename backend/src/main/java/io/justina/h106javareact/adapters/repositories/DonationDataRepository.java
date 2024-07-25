package io.justina.h106javareact.adapters.repositories;

import io.justina.h106javareact.domain.entities.DonationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationDataRepository extends JpaRepository<DonationData, String> {
}
