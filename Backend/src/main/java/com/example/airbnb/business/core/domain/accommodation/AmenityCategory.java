package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Entity
public class AmenityCategory {

    @Id
    @Column(name = "amenity_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityCategoryId;

    @NotNull @NotEmpty
    @Enumerated(EnumType.STRING)
    private AmenityType amenityType;

    @NotNull @NotEmpty
    @Column(length = 20)
    private String name;

    @NotNull
    @Column(length = 100)
    private String description;

    public AmenityCategory() {
    }

    public AmenityCategory(String name, String description) {
        this.name = convert(name);
        this.description = description;
    }

    private String convert(String name) {
        if (name == null) {
            return "";
        }
        return name;
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
