package com.example.airbnb.business.web.controller.city;

import com.example.airbnb.business.web.controller.city.dto.NearByCity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/{cityName}")
    public List<NearByCity> searchAccommodations(@PathVariable String cityName) {
        return cityService.findNearByCitiesBy(cityName)
                .stream()
                .map(NearByCity::new)
                .collect(Collectors.toList());
    }
}