package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AccommodationResponse {

    private Long accommodationId;
    private Long hostId;
    private String hostName;
    private String hostProfileImage;
    private String name;
    private String description;
    private BigDecimal oneDayPerPrice;
    private String address;
    private AccommodationType accommodationType;
    private Double averageRating;
    private List<String> images;
    private Comments comments;
    private AmenityCategories amenityCategories;
    private AccommodationOptionLines accommodationOptionLines;
    private int commentSize;
    private int maxNumberOfPeople;
    private int bedRooms;
    private int beds;
    private int bathRooms;

    public AccommodationResponse() {
    }

    public void add(List<Image> images, List<AmenitySubCategory> amenitySubCategories, List<Comment> comments, List<AccommodationOptionLine> accommodationOptionLines) {
        addImages(images);
        addCategories(amenitySubCategories);
        addComments(comments);
        addAccommodationOptionLines(accommodationOptionLines);
    }

    public void addImages(List<Image> images) {
        if (images.size() > 0)
            this.images = images.stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());
    }

    public void addCategories(List<AmenitySubCategory> subCategories) {
        if (subCategories.size() > 0)
            this.amenityCategories = new AmenityCategories(subCategories);
    }

    public void addComments(List<Comment> comments) {
        if (comments.size() > 0) {
            this.comments = new Comments(comments);
            this.commentSize = comments.size();
        }
    }

    private void addAccommodationOptionLines(List<AccommodationOptionLine> accommodationOptionLines) {
        if (accommodationOptionLines.size() > 0)
            this.accommodationOptionLines = new AccommodationOptionLines(accommodationOptionLines);
    }
}
