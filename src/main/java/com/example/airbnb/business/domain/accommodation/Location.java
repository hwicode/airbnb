package com.example.airbnb.business.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Location {

    private double longtitude;
    private double latitude;

    public Location(double longtitude, double latitude) {
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    protected Location (){}
}

