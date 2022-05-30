package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationResponse;
import com.example.airbnb.business.web.service.AccommodationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationReadService accommodationReadService;

    @GetMapping("{id}")
    public AccommodationResponse searchAccommodationDetail(@PathVariable("id") Long id) {
        return new AccommodationDeatilResponse(accommodationReadService.findByAccommodationId(id));
    }
}
