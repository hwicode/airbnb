package com.example.airbnb.business.web.service.place;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceReadService {

    private final AccommodationRepository accommodationRepository;

    @Transactional(readOnly = true)
    public List<Accommodation> searchAccommodationsByPlace(Double latitude, Double longitude) {
        String point = String.format("point(%s %s)", longitude, latitude);
        String searchRange = "3000";

        return accommodationRepository.findAccommodationByPoint(point, searchRange);
    }
}
