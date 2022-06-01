package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;

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

}
