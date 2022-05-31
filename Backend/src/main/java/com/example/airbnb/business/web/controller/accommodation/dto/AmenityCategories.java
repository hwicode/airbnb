package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.AmenityCategory;
import com.example.airbnb.business.core.domain.accommodation.AmenitySubCategory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Getter
public class AmenityCategories {

    private List<AmenityCategoryResponse> amenityCategoryResponses;

    public AmenityCategories(List<AmenitySubCategory> subCategories) {
        this.amenityCategoryResponses = createAmenityCategoryResponses(subCategories);
    }

    private List<AmenityCategoryResponse> createAmenityCategoryResponses(List<AmenitySubCategory> subCategories) {
        Map<AmenityCategory, List<AmenitySubCategory>> categories = subCategories.stream()
                .collect(groupingBy(AmenitySubCategory::getAmenityCategory));

        List<AmenityCategoryResponse> amenityCategoryResponses = new ArrayList<>();

        for (AmenityCategory key : categories.keySet()) {
            AmenityCategoryResponse response = new AmenityCategoryResponse(key, categories.get(key));
            amenityCategoryResponses.add(response);
        }
        return amenityCategoryResponses;
    }
}
