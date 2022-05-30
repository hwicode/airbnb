package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationDeatilResponse;
import com.example.airbnb.business.web.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("{id}")
    public AccommodationDeatilResponse searchAccommodationDetail(@PathVariable("id") Long id) {
        return new AccommodationDeatilResponse(accommodationService.findByAccommodationId(id));
    }
}
