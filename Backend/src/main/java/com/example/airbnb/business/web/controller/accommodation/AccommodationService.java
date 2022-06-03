package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationService {

    public List<Accommodation> registAccommodation(AccommodationRegistRequest request) {
        return null;
    }
}
