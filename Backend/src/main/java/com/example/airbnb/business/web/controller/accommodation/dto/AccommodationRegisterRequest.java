package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AccommodationRegisterRequest {

    private AccommodationType accommodationType;
    private String gu;
    private String street;
    private String zipCode;
    private Double averageRating;
    private String description;
    private Double latitude;
    private Double longitude;
    private String mainImageUrl;
    private Integer maxNumberOfPeople;
    private String roomName;
    private BigDecimal oneDayPerPrice;
    private Integer bathRooms;
    private Integer bedRooms;
    private Integer beds;
    private List<List<String>> imageStrings;
    private String cityName;
    private String githubId;

    public AccommodationRegisterRequest(){}

    public Accommodation toEntity() {
        Address address = new Address(gu, street);
        Location location = new Location(longitude, latitude);
        Room room = new Room(beds, bathRooms, bedRooms);
        List<Image> images = createImages();

        return Accommodation.builder()
                .name(roomName)
                .description(description)
                .mainImageUrl(mainImageUrl)
                .price(oneDayPerPrice)
                .averageRating(averageRating)
                .room(room)
                .accommodationType(accommodationType)
                .address(address)
                .location(location)
                .images(images)
                .maxNumberOfPeople(maxNumberOfPeople)
                .build();
    }

    private List<Image> createImages() {
        List<Image> images = new ArrayList<>();
        for (List<String> imageString : imageStrings) {
            images.add(Image.createImage(imageString));
        }

        return images;
    }

}
