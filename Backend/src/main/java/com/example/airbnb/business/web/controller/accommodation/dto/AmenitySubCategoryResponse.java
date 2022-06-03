package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.AmenitySubCategory;
import lombok.Getter;

@Getter
public class AmenitySubCategoryResponse {
    private String name;

    public AmenitySubCategoryResponse(AmenitySubCategory amenitySubCategory) {
        this.name = amenitySubCategory.getName();
    }
}
