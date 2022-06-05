package com.example.airbnb.business.web.controller.place;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;

import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationSearchResponse;
import com.example.airbnb.business.web.service.place.PlaceReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceReadService placeReadService;

    @GetMapping
    public List<AccommodationSearchResponse> searchAccommodationsByPlace(@RequestParam Double latitude, @RequestParam Double longitude) {
        return placeReadService.searchAccommodationsByPlace(latitude, longitude)
                .stream()
                .map(Accommodation::toSearchResponse)
                .collect(Collectors.toList());
    }
}
