package com.example.airbnb.business.core.repository.accommodation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

    Optional<Accommodation> findByAccommodationId(Integer id);
}
