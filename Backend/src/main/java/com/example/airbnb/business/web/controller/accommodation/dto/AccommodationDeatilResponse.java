package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.AccommodationType;
import com.example.airbnb.business.core.domain.accommodation.Address;
import com.example.airbnb.business.core.domain.accommodation.Image;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AccommodationDeatilResponse {

    private Long accommodationId;
    private String name;
    private String description;
    private BigDecimal oneDayPrice;
    private Double averageRating;
    private int commentNumber;
    private Address address;
    private AccommodationType typeOfAccommodation;
    private String host;
    private int maxNumberOfPeople;
    private int bedRooms;
    private int beds;
    private int bathRooms;
    private List<String> images;

    public AccommodationDeatilResponse(Accommodation accommodation) {
        this.accommodationId = accommodation.getAccommodationId();
        this.name = accommodation.getName();
        this.description = accommodation.getDescription();
        this.oneDayPrice = accommodation.getPrice();
        this.averageRating = accommodation.getAverageRating();
//        this.commentNumber = countComments(accommodation);
        this.address = accommodation.getAddress();
        this.typeOfAccommodation = accommodation.getAccommodationType();
        this.host = getHostName(accommodation);
        this.maxNumberOfPeople = accommodation.getMaxNumberOfPeople();
        this.bedRooms = getBedRooms(accommodation);
        this.beds = getBeds(accommodation);
        this.bathRooms = getBathRooms(accommodation);
        this.images = changeImages(accommodation);
    }

    private List<String> changeImages(Accommodation accommodation) {
        return accommodation.getImages().stream()
                .map(Image::getImageUrl)
                .collect(Collectors.toList());
    }

    public AccommodationDeatilResponse() {
    }

    private int getBathRooms(Accommodation accommodation) {
        return accommodation.getRoom().getBathRooms();
    }

    private int getBeds(Accommodation accommodation) {
        return accommodation.getRoom().getBeds();
    }

    private int getBedRooms(Accommodation accommodation) {
        return accommodation.getRoom().getBedRooms();
    }

    private String getHostName(Accommodation accommodation) {
        return accommodation.getMember().getName();
    }

}
