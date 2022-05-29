package com.example.airbnb.business.web.controller;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import lombok.Getter;

@Getter
public class AccommodationDetailResponse {

    private Integer accommodationId;

    public AccommodationDetailResponse(Accommodation accommodation) {
        this.accommodationId = accommodation.getAccommodationId();
    }
}
