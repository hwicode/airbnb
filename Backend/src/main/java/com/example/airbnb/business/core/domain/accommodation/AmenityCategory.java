package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class AmenityCategory {

    @Id
    @Column(name = "amenity_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @OneToMany(mappedBy = "amenityCategory", cascade = CascadeType.PERSIST)
    private List<AmenitySubCategory> subCategories = new ArrayList<>();

    public AmenityCategory(String name) {
        this.name = name;
    }

    public void registSubCategory(List<AmenitySubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    protected AmenityCategory() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmenityCategory that = (AmenityCategory) o;
        return categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
}
