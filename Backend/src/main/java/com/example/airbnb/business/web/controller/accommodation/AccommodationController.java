package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.web.controller.accommodation.dto.*;
import com.example.airbnb.business.web.service.accommodation.AccommodationReadService;
import com.example.airbnb.business.web.service.accommodation.AccommodationService;
import com.example.airbnb.common.geometry.objects.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public List<SearchPriceResponse> getPriceRange(@RequestParam("tagName") String tagName,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkIn, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOut) {
        return accommodationReadService.findAccommodationPriceRangeByTagAndPeriod(tagName, checkIn, checkOut);
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

    @GetMapping("/search")
    public List<AccommodationSearchResponse> searchAccommodations(@RequestParam String tagName,
                                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate checkIn
                                                                  , @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate checkOut
                                                                  , @RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice
                                                                  , @RequestParam int adults, @RequestParam int children
                                                                  , @RequestParam int infants , @RequestParam Double latitude
                                                                  , @RequestParam Double longitude) {
        AccommodationSearchCondition searchCondition = new AccommodationSearchCondition(tagName, checkIn, checkOut, minPrice, maxPrice,
                adults, children, infants, latitude, longitude);

        return accommodationReadService.searchAccommodations(searchCondition);
    }

    public Position calculateDistance(double lng, double log) {
        return accommodationReadService.cal(lng, log);
    }
}
