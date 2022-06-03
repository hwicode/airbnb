package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.AmenityCategory;
import com.example.airbnb.business.core.domain.accommodation.AmenitySubCategory;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AmenityCategoryResponse {

    private String name;
    private List<AmenitySubCategoryResponse> amenitySubCategoryResponses;

    public AmenityCategoryResponse(AmenityCategory key, List<AmenitySubCategory> amenitySubCategories) {
        this.name = key.getName();
        this.amenitySubCategoryResponses = createAmenitySubCategoryResponse(amenitySubCategories);
    }

    private List<AmenitySubCategoryResponse> createAmenitySubCategoryResponse(List<AmenitySubCategory> amenitySubCategories) {
        return amenitySubCategories.stream()
                .map(AmenitySubCategoryResponse::new)
                .collect(Collectors.toList());
    }
}
