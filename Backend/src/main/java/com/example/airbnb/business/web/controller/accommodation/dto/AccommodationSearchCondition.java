package com.example.airbnb.business.web.controller.accommodation.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AccommodationSearchCondition {

    private String tagName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int adults;
    private int children;
    private int infants;
    private Double latitude;
    private Double longitude;

    public AccommodationSearchCondition (){};

    public AccommodationSearchCondition(String tagName, LocalDate checkIn, LocalDate checkOut, BigDecimal minPrice, BigDecimal maxPrice,
                                        int adults, int children, int infants, Double latitude, Double longitude) {
        this.tagName = tagName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
