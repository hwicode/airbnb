package com.example.airbnb.business.web.controller.city;

import com.example.airbnb.business.web.controller.city.dto.NearByCity;
import com.example.airbnb.business.web.service.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<NearByCity> getCities() {
        return cityService.getCities()
                .stream()
                .map(NearByCity::new)
                .collect(Collectors.toList());
    }
}
