package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.AccommodationType;
import com.example.airbnb.business.core.domain.accommodation.Image;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class AccommodationRegisterResponse {

    private AccommodationType accommodationType;
    private String address;
    private Double averageRating;
    private String description;
    private String mainImageUrl;
    private Integer maxNumberOfPeople;
    private String roomName;
    private BigDecimal oneDayPerPrice;
    private Integer bathRooms;
    private Integer bedRooms;
    private Integer beds;
    private List<Image> images;
    private String cityName;
    private String githubId;

    public AccommodationRegisterResponse(Accommodation accommodation){
        this.accommodationType = accommodation.getAccommodationType();
        this.address = accommodation.getAddress().getHomeAddress();
        this.averageRating = accommodation.getAverageRating();
        this.description = accommodation.getDescription();
        this.mainImageUrl = accommodation.getMainImageUrl();
        this.maxNumberOfPeople = accommodation.getMaxNumberOfPeople();
        this.roomName = accommodation.getName();
        this.oneDayPerPrice = accommodation.getPrice();
        this.bathRooms = accommodation.getRoom().getBathRooms();
        this.bedRooms = accommodation.getRoom().getBedRooms();
        this.beds = accommodation.getRoom().getBeds();
        this.images = accommodation.getImages();
        this.cityName = accommodation.getCity().getName();
        this.githubId = accommodation.getMember().getGithubId();
    }
}
