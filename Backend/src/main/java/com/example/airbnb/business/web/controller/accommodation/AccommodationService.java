package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    public List<Accommodation> registAccommodation(AccommodationRegistRequest request) {
        return null;
    }
}
