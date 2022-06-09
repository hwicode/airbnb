package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.*;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AccommodationRegisterRequest {

    @NotBlank
    private AccommodationType accommodationType;

    @NotNull @NotBlank
    private String gu;

    @NotNull @NotBlank
    private String street;

    @NotBlank
    private Double averageRating;

    @NotNull @NotBlank
    private String description;

    @NotNull
    private String mainImageUrl;

    @NotNull
    private Integer maxNumberOfPeople;

    @NotNull @NotEmpty
    private String roomName;

    @NotNull
    private BigDecimal oneDayPerPrice;

    @NotNull
    private Integer bathRooms;

    @NotNull
    private Integer bedRooms;

    @NotNull
    private Integer beds;

    @NotNull
    private List<List<String>> imageStrings;

    @NotNull @NotEmpty
    private String cityName;

    public AccommodationRegisterRequest() {
    }

    public Accommodation toEntity() {
        Address address = new Address(gu, street);
        Room room = new Room(beds, bathRooms, bedRooms);

        return Accommodation.builder()
                .name(roomName)
                .description(description)
                .mainImageUrl(mainImageUrl)
                .price(oneDayPerPrice)
                .room(room)
                .accommodationType(accommodationType)
                .address(address)
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
