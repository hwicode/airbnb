package com.example.airbnb.business.web.controller.accommodation.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SearchPriceResponse {
    private Long accommodationId;
    private BigDecimal price;

    public SearchPriceResponse(Long accommodationId, BigDecimal price) {
        this.accommodationId = accommodationId;
        this.price = price;
    }

    public SearchPriceResponse() {
    }
}
