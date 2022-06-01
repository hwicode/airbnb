package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.AccommodationType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccommodationInCityResponse {

    private Long accommodationId;
    private String roomName;
    private String address;
    private AccommodationType accommodationType;
    private double averageRating;
    private BigDecimal oneDayPerPrice;

    private String image;
    private int commentNumber;

    public AccommodationInCityResponse() {

    }

}
