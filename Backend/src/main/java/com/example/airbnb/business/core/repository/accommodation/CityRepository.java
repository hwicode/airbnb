package com.example.airbnb.business.core.repository.accommodation;

import com.example.airbnb.business.core.domain.accommodation.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findCityByName(String cityName);

    List<City> findCitiesBy(String cityName);
}
