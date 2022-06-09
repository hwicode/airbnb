package com.example.airbnb.business.web.controller.accommodation.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AccommodationSearchCondition {

    @NotNull @NotBlank
    private String tagName;

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private LocalDate checkOut;

    @NotNull
    private BigDecimal minPrice;

    @NotNull
    private BigDecimal maxPrice;

    @NotNull
    private Integer adults;

    @NotNull
    private Integer children;

    @NotNull
    private Integer infants;

    public AccommodationSearchCondition (){};

    public AccommodationSearchCondition(String tagName, LocalDate checkIn, LocalDate checkOut, BigDecimal minPrice, BigDecimal maxPrice,
                                        int adults, int children, int infants) {
        this.tagName = tagName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
    }
}
