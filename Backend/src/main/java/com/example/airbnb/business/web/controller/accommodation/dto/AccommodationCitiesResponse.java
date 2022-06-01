package com.example.airbnb.business.web.controller.accommodation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AccommodationCitiesResponse {

    private List<AccommodationInCityResponse> accommodations;

    public AccommodationCitiesResponse(List<AccommodationInCityResponse> accommodations) {
        this.accommodations = accommodations;
    }
}
