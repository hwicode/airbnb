package com.example.airbnb.business.web.controller.place;

import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationSearchResponse;
import com.example.airbnb.business.web.service.place.PlaceReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceReadService placeReadService;

    @GetMapping
    public List<AccommodationSearchResponse> searchAccommodationsByPlace(@Valid @NotNull @RequestParam Double latitude,
                                                                         @Valid @NotNull @RequestParam Double longitude) {
        return placeReadService.searchAccommodationsByPlace(latitude, longitude)
                .stream()
                .map(AccommodationSearchResponse::new)
                .collect(Collectors.toList());
    }
}
