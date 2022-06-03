package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.AmenityCategory;
import lombok.Getter;

@Getter
public class AmenitySubCategoryResponse {
    private String name;

    public AmenitySubCategoryResponse(AmenityCategory amenityCategory) {
        this.name = amenityCategory.getName();
    }
}
