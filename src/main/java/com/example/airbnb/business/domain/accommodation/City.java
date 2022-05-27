package com.example.airbnb.business.domain.accommodation;

import javax.persistence.*;

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
}
