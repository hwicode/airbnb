package com.example.airbnb.business.web.service.city;

import com.example.airbnb.business.core.domain.accommodation.City;
import com.example.airbnb.business.core.repository.accommodation.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<City> getCities() {
        return cityRepository.findAll();
    }
}
