package com.example.airbnb.business.web.controller.city;

import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationListInCityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodations")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities/{cityName}")
    public AccommodationListInCityResponse searchAccommodations(@PathVariable String cityName) {
        return cityService.findCitiesBy(cityName);
    }
}
