package com.example.airbnb.business.web.controller.accommodation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AccommodationListInCityResponse {

    private List<AccommodationInCityResponse> accommodations;

    public AccommodationListInCityResponse(List<AccommodationInCityResponse> accommodations) {
        this.accommodations = accommodations;
    }
}
