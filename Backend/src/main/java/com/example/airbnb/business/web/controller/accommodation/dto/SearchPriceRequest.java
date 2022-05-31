package com.example.airbnb.business.web.controller.accommodation.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SearchPriceRequest {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public SearchPriceRequest(BigDecimal minPrice, BigDecimal maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
