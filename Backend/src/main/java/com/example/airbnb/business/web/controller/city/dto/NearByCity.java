package com.example.airbnb.business.web.controller.city.dto;

import com.example.airbnb.business.core.domain.accommodation.City;
import lombok.Getter;

@Getter
public class NearByCity {

    private String name;
    private String image;
    private Double latitude;
    private Double longitude;

    public NearByCity(City city) {
        this.name = city.getName();
        this.image = city.getImage();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
    }
}
