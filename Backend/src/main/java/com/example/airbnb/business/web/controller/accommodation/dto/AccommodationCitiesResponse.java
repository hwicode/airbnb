package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.AccommodationType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class AccommodationCitiesResponse {


    private Long accommodationId;
    private String roomName;
    private String address;
    private AccommodationType accommodationType;
    private double averageRating;
    private BigDecimal oneDayPerPrice;

    private String image;
    private int commentNumber;

    public AccommodationCitiesResponse(Accommodation accommodation) {
        this.accommodationId = accommodation.getAccommodationId();
        this.roomName = accommodation.getName();
        this.address = accommodation.getAddress().toString();
        this.accommodationType = accommodation.getAccommodationType();
        this.averageRating = accommodation.getAverageRating();
        this.oneDayPerPrice = accommodation.getPrice();
        this.image = accommodation.getImages().get(0).getImageUrl();
    }
}
