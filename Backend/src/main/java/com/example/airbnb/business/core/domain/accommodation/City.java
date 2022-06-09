package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @NotNull @NotEmpty
    private String nation;

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    @Column(length = 1000)
    private String image;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

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
