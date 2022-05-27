package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    protected AmenityCategory() {

    }
}
