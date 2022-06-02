package com.example.airbnb.business.web.controller.accommodation;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AccommodationSearchCondition {

    private String address;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int adults;
    private int children;
    private int infants;
    private Double latitude;
    private Double longitude;


}
