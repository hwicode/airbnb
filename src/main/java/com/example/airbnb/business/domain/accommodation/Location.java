package com.example.airbnb.business.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.longtitude, longtitude) == 0 && Double.compare(location.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longtitude, latitude);
    }
}

