package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.AccommodationOptionLine;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AccommodationOptionLines {
    private List<AccommodationOptionLineResponse> accommodationOptionLineResponses;

    public AccommodationOptionLines(List<AccommodationOptionLine> accommodationOptionLines) {
        this.accommodationOptionLineResponses = createAccommodationOptionLine(accommodationOptionLines);
    }

    private List<AccommodationOptionLineResponse> createAccommodationOptionLine(List<AccommodationOptionLine> accommodationOptionLines) {
        return accommodationOptionLines.stream()
                .map(AccommodationOptionLineResponse::new)
                .collect(Collectors.toList());
    }
}
