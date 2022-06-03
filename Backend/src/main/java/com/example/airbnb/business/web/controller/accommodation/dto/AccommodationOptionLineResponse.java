package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.AccommodationOptionLine;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccommodationOptionLineResponse {

    private String name;
    private BigDecimal price;

    public AccommodationOptionLineResponse(AccommodationOptionLine accommodationOptionLine) {
        this.name = accommodationOptionLine.getName();
        this.price = accommodationOptionLine.getPrice();
    }
}
