package com.example.airbnb.business.core.domain.accommodation;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    private String nation;

    private String name;

    private String image;

    public City(String nation, String name, String image) {
        this.nation = nation;
        this.name = name;
        this.image = image;
    }

    protected City(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}
