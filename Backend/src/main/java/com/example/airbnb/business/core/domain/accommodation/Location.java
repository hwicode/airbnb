package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Location {

    @Column(name = "range", columnDefinition = "Polygon")
    private Polygon range;
    private Point point;

    public Location(Double latitude, Double longitude) {
        this.point = toPoint(latitude, longitude);
    }

    public Point toPoint(Double latitude, Double longitude) {
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        try {
            return (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new IllegalStateException("Point 변환과정에서 예외가 발생하였습니다.");
        }
    }

    protected Location (){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(point, location.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}

