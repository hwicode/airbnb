package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
public class Amenity {

    @Id
    @Column(name = "amenity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_category_id")
    private AmenityCategory amenityCategory;

    protected Amenity(){};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenity amenity = (Amenity) o;
        return amenityId.equals(amenity.amenityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amenityId);
    }
}
