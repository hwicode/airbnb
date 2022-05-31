package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationResponse;
import com.example.airbnb.business.web.controller.accommodation.dto.SearchPriceRequest;
import com.example.airbnb.business.web.service.accommodation.AccommodationReadService;
import com.example.airbnb.common.geometry.objects.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationReadService accommodationReadService;

    @GetMapping("{id}")
    public AccommodationResponse searchAccommodationDetail(@PathVariable("id") Long id) {
        return accommodationReadService.findByAccommodationId(id);
    }

    @GetMapping
    public List<SearchPriceResponse> getPriceRange(@RequestBody SearchPriceRequest request){
        return accommodationReadService.findAccommodationPriceRangeBy(request.getMinPrice(), request.getMaxPrice());
    }

    public Position calculateDistance(double lng, double log){
        return accommodationReadService.cal(lng, log);
    }
}
