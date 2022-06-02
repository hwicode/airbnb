package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationRelatedCityResponse;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationResponse;
import com.example.airbnb.business.web.service.accommodation.AccommodationReadService;
import com.example.airbnb.common.geometry.objects.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/api/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final AccommodationReadService accommodationReadService;

    //
    @GetMapping("{id}")
    public AccommodationResponse searchAccommodationDetail(@PathVariable("id") Long id) {
        return accommodationReadService.findByAccommodationId(id);
    }

    //
    @GetMapping("/tag")
    public List<SearchPriceResponse> getPriceRange(@RequestParam("tagName") String tagName) {
        return accommodationReadService.findAccommodationPriceRangeBy(tagName);
    }

    //
    @GetMapping("/cities")
    public List<AccommodationRelatedCityResponse> findByAccommodationsByCityName(@RequestParam("cityName") String cityName) {
        return accommodationReadService.findByAccommodationsByCityName(cityName);
    }

    @PostMapping
    public List<AccommodationRegistResponse> registAccommodation(@RequestBody AccommodationRegistRequest request) {
        return accommodationService.registAccommodation(request).stream()
                .map(AccommodationRegistResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping()
    public List<AccommodationSearchResponse> searchAccommodations(@RequestBody AccommodationSearchCondition searchCondition) {
        return accommodationReadService.searchAccommodations(searchCondition);
    }

    public Position calculateDistance(double lng, double log) {
        return accommodationReadService.cal(lng, log);
    }
}