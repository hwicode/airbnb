package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "amenity_category")
public class AmenityCategory {

    @Id
    @Column(name = "amenity_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityCategoryId;

    @Enumerated(EnumType.STRING)
    private AmenityType amenityType;
    private String name;
    private String description;

    public AmenityCategory() {
    }

    public AmenityCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmenityCategory that = (AmenityCategory) o;
        return amenityCategoryId.equals(that.amenityCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amenityCategoryId);
    }
}
