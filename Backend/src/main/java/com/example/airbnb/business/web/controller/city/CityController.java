package com.example.airbnb.business.web.controller.city;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accommodations")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities/{cityName}")
    public List<NearByCity> searchAccommodations(@PathVariable String cityName) {
        return cityService.findNearByCitiesBy(cityName)
                .stream()
                .map(NearByCity::new)
                .collect(Collectors.toList());
    }
}
