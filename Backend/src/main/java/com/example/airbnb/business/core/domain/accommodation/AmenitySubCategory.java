package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
public class AmenitySubCategory {

    @Id
    @Column(name = "amenity_subcategory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "amenity_category_id")
    private AmenityCategory amenityCategory;

    public AmenitySubCategory() {
    }

    public AmenitySubCategory(String name, String description, AmenityCategory amenityCategory) {
        this.name = name;
        this.description = description;
        this.amenityCategory = amenityCategory;
        regist(amenityCategory);
    }

    void regist(AmenityCategory amenityCategory) {
        if (amenityCategory != null) {
            amenityCategory.getSubCategories().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmenitySubCategory that = (AmenitySubCategory) o;
        return subCategoryId.equals(that.subCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategoryId);
    }
}
