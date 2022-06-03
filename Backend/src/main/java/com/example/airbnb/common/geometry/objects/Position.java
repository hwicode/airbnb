package com.example.airbnb.common.geometry.objects;

import lombok.Getter;

@Getter
public class Position {

    private Double latitude;
    private Double longitude;

    public Position(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
