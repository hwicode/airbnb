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
    private int commentSize;
    private int maxNumberOfPeople;
    private int bedRooms;
    private int beds;
    private int bathRooms;

    public AccommodationResponse() {
    }

    public void addImages(List<Image> images) {
        this.images = changeImages(images);
    }

    private List<String> changeImages(List<Image> images) {
        return images.stream()
                .map(Image::getImageUrl)
                .collect(Collectors.toList());
    }

    public void addComments(List<Comment> comments) {
        this.comments = createComments(comments);
        this.commentSize = comments.size();
    }

    private Comments createComments(List<Comment> comments) {
        return new Comments(comments);
    }

    public void addCategories(List<AmenitySubCategory> subCategories) {
        this.amenityCategories = new AmenityCategories(subCategories);
    }

}
